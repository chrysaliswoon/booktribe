package vttp.project.booktribe.model;

import jakarta.json.JsonArray;
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

    
    
}
