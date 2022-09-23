package vttp.project.booktribe.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.Data;

@Data
public class Quote {

    private String text;
    private String author;
    private String tag;

    public static Quote create(JsonObject jo) {

        Quote quote = new Quote();
        quote.setText(jo.getString("text"));
        quote.setAuthor(jo.getString("author"));
        quote.setTag(jo.getString("tag"));

        return quote;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("text", text)
        .add("author", author)
        .add("tag", tag)

        .build();
    }

    public static Quote createQuote(String text, String author, String tag) {
        Quote quoteData = new Quote();
        quoteData.setText(text);
        quoteData.setAuthor(author);
        quoteData.setTag(tag);

        return quoteData;
    }
    
}
