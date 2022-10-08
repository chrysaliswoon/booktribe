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

    public void createProfile(User user) {
        userRepo.create(user.getEmail(), user.toMap());
    }

    public void updateProfile(String email, String key, String value) {
        userRepo.updateUser(email, key, value);
    }

    public void deleteProfile(String email) {
        userRepo.deleteUser(email);
        System.out.println("User has been deleted");
    }

    public boolean login(String email, String password) {

        //? Get Redis Value from the Database
        Optional<User> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 
        
        //? Check if password is correct
        String redisPassword = redisValue.get().getPassword();
        if (password.equals(redisPassword)) {
            return true;
        }

        return false;
    }

    public boolean checkProfile(String email) {

        //? Get Redis Value from the Database
        Optional<User> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 


        //? Check if the profile exists in Redist
        String redis_profile = redisValue.get().getProfile();
        
        //? Check if password is correct
        if (redis_profile.isBlank()) {
            return false;
        }

        return true;
    }


    public User userDetails(String email) {
        Optional<User> userOpt = userRepo.findUserByEmail(email);
        return userOpt.get();
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
