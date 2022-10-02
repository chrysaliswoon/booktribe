 package vttp.project.booktribe.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.repository.ShelfRepository;

@Service
public class BookService {

    // ? Instantiate the Book Repository to use Redis database
    @Autowired
    private ShelfRepository bookRepo;

    // ? API URL
    private static String apiExploreBookUrl = "https://www.googleapis.com/books/v1/volumes";
    private static String apiSpecificBookUrl = "https://www.googleapis.com/books/v1/volumes/{id}";

    // ? Inject value into fields
    @Value("${API_KEY}")
    private String apiKey;

    //? Get ALL books
    public List<Book> exploreBooks(String book) {

        // ? Create endpoint URL with query string
        String url = UriComponentsBuilder.fromUriString(apiExploreBookUrl)
                .queryParam("key", apiKey)
                .queryParam("q", book)
                .toUriString();

        // ? Create GET Request
        RequestEntity<Void> req = RequestEntity.get(url).build();

        // ? Make call to Book API
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res;

        try {
            res = template.exchange(req, String.class);
        } catch (Exception ex) {
            System.err.printf("Error: ", ex.getMessage());
            return Collections.emptyList();
        }

        // ? Get body with the payload
        String payload = res.getBody();
        // ? Convert payload to JSON object
        Reader strReader = new StringReader(payload);

        // ? Create JSONReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);

        // ? Reads payload as JSON object
        JsonObject bookResult = jsonReader.readObject();
        
        
        // ? Get array within the object
        JsonArray bookData = bookResult.getJsonArray("items");
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < bookData.size(); i++) {
            JsonObject object = bookData.getJsonObject(i);
            String id = object.getString("id");
            // System.out.println(id);
            JsonObject volInfo = object.getJsonObject("volumeInfo");
            JsonObject imgLinks = volInfo.getJsonObject("imageLinks");
            String imgUrl = imgLinks.getString("thumbnail");
            String title = volInfo.getString("title");
            JsonArray authors = volInfo.getJsonArray("authors");
            JsonArray categories = volInfo.getJsonArray("categories");
            // System.out.printf("%s, %s, %s, %s", id, imgUrl, title, authors.toString());

            // List<String> authorList = new ArrayList<String>();
            // for (int j = 0; j < authors.size(); j++) {
            //     String author = authors.getString(j);
            //     System.out.println(">> adding author " + author);
            //     authorList.add(author);
            // }

            // String authorName = authorList.get(0);
            // System.out.print(authorName);
            
            list.add(Book.createBook(id, imgUrl, title, authors, categories));
        }
        return list;
    }

    //? Get specific book details
    public List<Book> bookDetails(String id) {

        // URI (URL) parameters
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", id);

        // ? Create endpoint URL with query string
        String url = UriComponentsBuilder.fromUriString(apiSpecificBookUrl)
                .queryParam("key", apiKey)
                .buildAndExpand(urlParams)
                .toUriString();

        // ? Create GET Request
        RequestEntity<Void> req = RequestEntity.get(url).build();

        // ? Make call to Book API
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res;

        try {
            res = template.exchange(req, String.class);
        } catch (Exception ex) {
            System.err.printf("Error: ", ex.getMessage());
            return Collections.emptyList();
        }

        // ? Get body with the payload
        String payload = res.getBody();

        // ? Convert payload to JSON object
        Reader strReader = new StringReader(payload);

        // ? Create JSONReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);

        // ? Reads payload as JSON object
        JsonObject bookResult = jsonReader.readObject();

        ArrayList<Book> list = new ArrayList<>();
            JsonObject volInfo = bookResult.getJsonObject("volumeInfo");
            JsonObject imgLinks = volInfo.getJsonObject("imageLinks");
            String imgUrl = imgLinks.getString("thumbnail");
            String title = volInfo.getString("title");
            String subtitle = volInfo.getString("subtitle");
            String description = volInfo.getString("description");
            JsonArray authors = volInfo.getJsonArray("authors");
            // List<String> authorList = new ArrayList<String>();
            // for (int i = 0; i < authors.size(); i++) {
            // String author = authors.getString(i);
            // authorList.add(author);
            // }
            // String authorName = authorList.get(0);
            JsonArray categories = volInfo.getJsonArray("categories");

            list.add(Book.createSpecificBook(id, imgUrl, title, subtitle, description, authors, categories));
            
        return list;
        
    }


}
