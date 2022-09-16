package vttp.project.booktribe.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.project.booktribe.model.User;

@Service
public class UserService {

    //? API URL
    private static String userProfileURL = "https://vttp-booktribe.herokuapp.com/api/user/{id}";

    //? Get user profile details
    public List <User> userProfile(String email) {
        
        //? URI (URL) parameters
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", email);

        //? Create endpoint URL with query string
        String URL = UriComponentsBuilder.fromUriString(userProfileURL)
            .buildAndExpand(urlParams)
            .toUriString();
        
        //? Create GET Request
        RequestEntity<Void> req = RequestEntity.get(URL).build();

        //? Make call to User API in Redis Database
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res;

        try {
            res = template.exchange(req, String.class);
        } catch (Exception ex) {
            System.err.printf("Error: ", ex.getMessage());
            return Collections.emptyList();
        }

        // ? Get body with the payload
        String payload = res.getBody();

        // ? Convert payload to JSON object
        Reader strReader = new StringReader(payload);
        
        // ? Create JSONReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);

        //? Reads payload as Array of JSON object
        JsonObject profileObj = jsonReader.readObject();

        ArrayList<User> userProfile = new ArrayList<>();
            String user_name = profileObj.getString("name");
            String user_email = profileObj.getString("email");
            String user_profile = profileObj.getString("profile");

            userProfile.add(User.createUserProfile(user_name, user_email, user_profile));

        return userProfile;
        
    }
    
}
