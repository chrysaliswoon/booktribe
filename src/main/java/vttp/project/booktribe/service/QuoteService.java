package vttp.project.booktribe.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private static final String API = "https://goquotes-api.herokuapp.com/api/v1/random?count=1";

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

        // ? Reads payload as JSON object
        JsonObject apiResult = jsonReader.readObject();

        // ? Get array within the object
        JsonArray quotes = apiResult.getJsonArray("quotes");

        // System.out.println(quotes);

        ArrayList<Quote> quote = new ArrayList<>();
        JsonObject quoteInfo = quotes.getJsonObject(0);
        String text = quoteInfo.getString("text");
        String author = quoteInfo.getString("author");
        String tag = quoteInfo.getString("tag");

        quote.add(Quote.createQuote(text, author, tag));
        return quote;
    }


    
}
