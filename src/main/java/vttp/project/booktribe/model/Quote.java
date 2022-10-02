package vttp.project.booktribe.model;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import lombok.Data;

@Data
public class Quote {

    private String text;
    private String content;
    private String author;
    private String tag;
    private JsonArray tags;

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

    public static Quote createPoem(String text, String author, String tag) {
        Quote poemData = new Quote();
        poemData.setText(text);
        poemData.setAuthor(author);
        poemData.setTag(tag);

        return poemData;
    }

    public static Quote createQuote(String author, String content, JsonArray tags) {
        Quote quoteData = new Quote();
        quoteData.setAuthor(author);
        quoteData.setContent(content);
        quoteData.setTags(tags);


        return quoteData;
    }
    
}
