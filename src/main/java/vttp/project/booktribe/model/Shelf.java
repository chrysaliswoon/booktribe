package vttp.project.booktribe.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.Data;

@Data
public class Shelf {

    private String id;
    private String title;


    public static Shelf addToShelf(String id, String title) {
        Shelf shelfData = new Shelf();

        shelfData.setId(id);
        shelfData.setTitle(title);

        return shelfData;
    }

    //? Creates it as a Json String or as a Json Object

    public static Shelf create(JsonObject jsonObj) {
        Shelf shelf = new Shelf();
        shelf.setId(jsonObj.getString("id"));
        shelf.setTitle(jsonObj.getString("title"));

        return shelf;
    }

    public static Shelf create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader jsReader = Json.createReader(strReader);

        return create(jsReader.readObject());
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("id", this.id)
        .add("title", this.title)
        
        .build();
    }
    
}
