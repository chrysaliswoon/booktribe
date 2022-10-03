package vttp.project.booktribe.service;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private JsonObject userJson; 

    public void createProfile(String email, String payload) {
        userRepo.create(email, payload);
    }

    public boolean login(String email, String password) {

        //? Get Redis Value from the Database
        Optional<String> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 

        //? Store the Value as a string called Payload
        String payload = redisValue.get();

        //? Convert the String to a JSON 
        userJson = toJson(payload);

        //? Check if the password is the same one in Redis
        String redis_password = userJson.getString("password");
        
        //? Check if password is correct
        if (password.equals(redis_password)) {
            return true;
        }

        return false;
    }

    public boolean checkProfile(String email) {

        //? Get Redis Value from the Database
        Optional<String> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 

        //? Store the Value as a string called Payload
        String payload = redisValue.get();

        //? Convert the String to a JSON 
        userJson = toJson(payload);

        //? Check if the profile exists in Redist
        String redis_profile = userJson.getString("profile");
        
        //? Check if password is correct
        if (redis_profile.isBlank()) {
            return false;
        }
        return true;
    }


    public User userDetails(String email) {
        return User.loginUser(userJson);
    }

    public Set<String> getUsers() {
        Optional<Set<String>> findUsers = userRepo.findAllUsers();
        Set<String> payload = findUsers.get();
        
        return payload;
    }

    //? Convert String --> JSON object
    public JsonObject toJson(String payload) {
        StringReader strReader = new StringReader(payload);
        JsonReader jsReader = Json.createReader(strReader);
        JsonObject createJson = jsReader.readObject();

        return createJson;

    }

    public List<User> userProfile() {
        return null;
    }

    
}
