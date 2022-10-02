package vttp.project.booktribe.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.Data;

@Data
public class Bookshelf {

    private String userID;
    private String bookID;
    private String title;
    private String subtitle;
    private JsonArray authors;
    private JsonArray categories;
    private String purchaseDate;
    private String description;
    private String imgUrl;
    private Boolean status;

    public Bookshelf() {

    }

    public Bookshelf(String userID, String bookID) {
        this.userID = userID;
        this.bookID = bookID;
    }

    public static Bookshelf addBookToShelf(String userID, String bookID) {
        Bookshelf shelfData = new Bookshelf(userID, bookID);
        
        shelfData.setUserID(userID);
        shelfData.setBookID(bookID);

        return shelfData;
    }

    public static Bookshelf showBooks(JsonObject jsonObj) {
        Bookshelf shelfData = new Bookshelf();

        shelfData.setUserID(jsonObj.getString("user"));
        shelfData.setBookID(jsonObj.getString("book"));

        return shelfData;
    }

        //? Convert Model --> JSON object
        public JsonObject toJson() {
            return Json.createObjectBuilder()
                .add("userID", userID)
                .add("bookID", bookID)
    
                .build();
        }

        public static Bookshelf create(JsonObject jsonObj) {
            Bookshelf shelf = new Bookshelf();
            shelf.setUserID(jsonObj.getString("user"));
            shelf.setBookID(jsonObj.getString("book"));

            return shelf;
        }

            //? Convert JSON object --> String
    public static Bookshelf create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader jsReader = Json.createReader(strReader);

        return create(jsReader.readObject());
    }

    
    
}
