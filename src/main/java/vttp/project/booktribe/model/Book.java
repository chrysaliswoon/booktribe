package vttp.project.booktribe.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.Data;

@Data
public class Book {

    private String id;
    private String title;
    private String subtitle;
    private JsonArray authors;
    private JsonArray categories;
    private String purchaseDate;
    private String description;
    private String imgUrl;

    public static Book createBook(String id, String imgUrl, String title, JsonArray authors, JsonArray categories) {
        Book bookData = new Book();

        bookData.setId(id);
        bookData.setImgUrl(imgUrl);
        bookData.setTitle(title);
        bookData.setAuthors(authors);
        bookData.setCategories(categories);
        
        return bookData;
    }

    public static Book createSpecificBook(String id, String imgUrl, String title, String subtitle, String description, JsonArray authorList, JsonArray categories) {
        Book bookData = new Book();

        bookData.setId(id);
        bookData.setImgUrl(imgUrl);
        bookData.setTitle(title);
        bookData.setSubtitle(subtitle);
        bookData.setDescription(description);
        bookData.setAuthors(authorList);
        bookData.setCategories(categories);
        
        return bookData;
    }

    //? Creates it as a Json String or as a Json Object

    public static Book create(JsonObject jsonObj) {
        Book book = new Book();
        book.setId(jsonObj.getString("id"));
        book.setTitle(jsonObj.getString("title"));
        book.setDescription(jsonObj.getString("description"));
        book.setImgUrl(jsonObj.getString("url"));

        return book;
    }

    public static Book create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader jsReader = Json.createReader(strReader);

        return create(jsReader.readObject());
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("id", this.id)
        .add("title", this.title)
        .add("subtitle", this.subtitle)
        .add("authors", this.authors)
        .add("categories", this.categories)
        .add("description", this.description)
        .add("imgUrl", this.imgUrl)

        .build();
    }


}
