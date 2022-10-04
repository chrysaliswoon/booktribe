package vttp.project.booktribe.model;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import lombok.Data;

@Data
public class Quote {

    private String title;
    private List<String> poemContent;
    private String content;
    private String author;
    private String tag;
    private JsonArray tags;

    public static Quote create(JsonObject jo) {

        Quote quote = new Quote();
        quote.setTitle(jo.getString("title"));
        quote.setAuthor(jo.getString("author"));
        quote.setTag(jo.getString("tag"));

        return quote;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("title", title)
        .add("author", author)
        .add("tag", tag)

        .build();
    }

    public static Quote createPoem(String title, String author, List<String> poemContent, String tag) {
        Quote poemData = new Quote();
        poemData.setTitle(title);
        poemData.setAuthor(author);
        poemData.setPoemContent(poemContent);
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
