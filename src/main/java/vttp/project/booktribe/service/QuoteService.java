package vttp.project.booktribe.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.project.booktribe.model.Quote;
import vttp.project.booktribe.repository.QuoteRepository;

@Service
public class QuoteService {

    private static final String API = "https://poetrydb.org/random";

    @Autowired
    private QuoteRepository quoteRepo;

    public List<Quote> getQuote() {
        
        //? Create GET Request
        RequestEntity<Void> req = RequestEntity.get(API).build();

        //? Make call to Quote API
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

        // ? Reads payload as an array
        // JsonObject apiResult = jsonReader.readObject();
        JsonArray apiResult = jsonReader.readArray();

        // ? Get array within the object
        JsonObject poem = apiResult.getJsonObject(0);

        ArrayList<Quote> quote = new ArrayList<>();
        String text = poem.getString("title");
        String author = poem.getString("author");
        JsonArray lines = poem.getJsonArray("lines");
        
        String lineCount = poem.getString("linecount");

        quote.add(Quote.createQuote(text, author, lineCount));
        return quote;
    }


    
}
