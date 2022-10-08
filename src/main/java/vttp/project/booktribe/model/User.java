package vttp.project.booktribe.model;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.Data;

@Data
public class User{

    private String name;
    private String username;
    private String email;
    private String password;
    private String profile;
    private String favourite;

    public User() {

    }

    public User (String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }


    public User (String name, String username, String email, String password, String profile, String favourite) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.favourite = favourite;
    }

    public User (String name, String username, String email, String password, String profile) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public User(String name, String username, String password, String profile) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public static User createUser(String name, String username, String email, String password, String profile,  String favourite) {
        User userData = new User(name, username, email, password, profile, favourite);

        userData.setName(name);
        userData.setUsername(username);
        userData.setEmail(email);
        userData.setPassword(password);
        userData.setProfile(profile);
        userData.setFavourite(favourite);


        return userData;
    }

	public static User loginUser(JsonObject jsonObj) {
        User userData = new User();

        userData.setName(jsonObj.getString("name"));
        userData.setUsername(jsonObj.getString("username"));
        userData.setEmail(jsonObj.getString("email"));
        userData.setProfile(jsonObj.getString("profile"));

        return userData;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", this.name);
        map.put("username", this.username);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("profile", this.profile);

        return map;
    }


    //? Convert Model --> JSON object
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", name)
            .add("username", username)
            .add("email", email)
            .add("password", password)
            .add("profile", profile)
            
            .build();
    }

    public static User create(JsonObject jsonObj) {
        User user = new User();
        user.setName(jsonObj.getString("name"));
        user.setUsername(jsonObj.getString("username"));
        user.setEmail(jsonObj.getString("email"));
        user.setPassword(jsonObj.getString("password"));
        user.setPassword(jsonObj.getString("profile"));
        return user;
    }

    //? Convert JSON object --> String
    public static User create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader jsReader = Json.createReader(strReader);

        return create(jsReader.readObject());
    }

 



    
}

