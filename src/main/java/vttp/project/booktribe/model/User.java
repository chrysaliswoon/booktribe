package vttp.project.booktribe.model;

import java.io.StringReader;

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

    public User() {

    }

    public User (String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String payload) {
	}

	public static User createUser(String name, String username, String email, String password) {
        User userData = new User(name, username, email, password);

        userData.setName(name);
        userData.setUsername(username);
        userData.setEmail(email);
        userData.setPassword(password);

        
        return userData;
    }


    //? Convert Model --> JSON object
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", name)
            .add("username", username)
            .add("email", email)
            .add("password", password)

            .build();
    }

    public static User create(JsonObject jsonObj) {
        User user = new User();
        user.setName(jsonObj.getString("name"));
        user.setUsername(jsonObj.getString("username"));
        user.setEmail(jsonObj.getString("email"));
        user.setPassword(jsonObj.getString("password"));

        return user;
    }

    //? Convert JSON object --> String
    public static User create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader jsReader = Json.createReader(strReader);

        return create(jsReader.readObject());
    }

    //? Convert String --> JSON object


    
}

