package vttp.project.booktribe.repository;


import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.project.booktribe.model.User;



@Repository
public class UserRepository {
        
    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    //? CREATE new user
    public void create(String redisKey, Map<String, String> payload) {
        HashOperations<String, String, String> hashOp = template.opsForHash();
        // ValueOperations<String, String> valueOp = template.opsForValue();
        hashOp.putAll(redisKey, payload);
    }

    //? READ user data
    //? FIND SPECIFIC USER
    public Optional<User> findUserByEmail(String email) {
        HashOperations<String, String, String> hashOp = template.opsForHash();
        // ValueOperations<String, String> valueOp = template.opsForValue();
        // String user = valueOp.get(email);
        String redisName = hashOp.get(email, "name");
        String redisUserName = hashOp.get(email, "username");
        String redisEmail = hashOp.get(email, "email");
        String redisPassword = hashOp.get(email, "password");
        String redisProfile = hashOp.get(email, "profile");

        User user = new User(redisName, redisUserName, redisEmail, redisPassword, redisProfile);
        if (null == redisEmail)
            return Optional.empty();
        return Optional.of(user);
    }

    //? UPDATE USER DETAILS
    // public Optional<String> UpdateProfile(String email) {
    //     ValueOperations<String, String> valueOp = template.opsForValue();
    // }


    //? FIND ALL USERS
    public Optional<Set<String>> findAllUsers() {
        Set<String> allKeys = template.keys("*");
        return Optional.of(allKeys);
    }

    //? DELETE USER
    public Optional<String> deleteUser(String email) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        String user = valueOp.getAndDelete(email);
        return Optional.of(user);
    }
    
}
