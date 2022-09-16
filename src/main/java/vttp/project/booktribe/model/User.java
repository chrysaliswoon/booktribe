package vttp.project.booktribe.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User implements Serializable{

    private String name;
    private String email;
    private String password;
    private String profile;

    // public static User create(String json) {
    // try (StringReader strReader = new StringReader(json)) {
    // JsonReader jsnReader = Json.createReader(strReader);
    // return create(jsnReader.readObject());
    // }
    // }

    // public JsonObject toJson() {
    //     return Json.createObjectBuilder()
    //         .add(this.email, Json.createObjectBuilder()
    //                 .add("name", this.name)
    //                 .add("password", this.password)
    //                 .add("profile", this.profile)
    //             )

    //         .build();
    // }
    //? Json Output
    // "email": {
    //     "name": "Test Person",
    //     "email": "test@gmail.com",
    //     "password": "500072"
    // },

    // public Map<String, String> userDetails() {
    //     Map<String, String> userProfile = new HashMap<>();
    //     userProfile.put("email", this.email);
    //     userProfile.put("name", this.name);
    //     userProfile.put("password", this.password);
    //     userProfile.put("profile", this.profile);

    //     return userProfile;
    // }

    // public void createUser(MultiValueMap<String, String> form) {
    //     this.email = form.getFirst("email").toLowerCase();
    //     this.name = form.getFirst("email").toUpperCase();
    //     this.password = form.getFirst("password").toLowerCase();
    //     this.profile = form.getFirst("profile").toLowerCase();

    // }
    
}

