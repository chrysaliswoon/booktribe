package vttp.project.booktribe.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void createProfile(String email, String payload) {
        userRepo.create(email, payload);
    }

    public void checkProfile(String email, String password) {

        //? Get Redis Value from the Database
        Optional<String> redisValue = userRepo.findUserByEmail(email);

        //? Store the Value as a string called Payload
        String payload = redisValue.get();

        //? Convert the String to a JSON 
        JsonObject userJson = toJson(payload);
        System.out.println(userJson);

        //? Check if the key (email) exists in Redis


        
        

        //? Check the email AND password is 


    }

    //? Convert String --> JSON object

    public JsonObject toJson(String payload) {
        StringReader strReader = new StringReader(payload);
        JsonReader jsReader = Json.createReader(strReader);
        JsonObject createJson = jsReader.readObject();

        return createJson;

    }
    
}
