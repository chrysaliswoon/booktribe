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
    
    public static User createUserProfile(String name, String email, String profile) {
        User userData = new User();

        userData.setName(name);
        userData.setEmail(email);
        userData.setPassword(profile);

        return userData;
    }


    
}

