package vttp.project.booktribe.repository;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ScanResult;


@Repository
public class UserRepository {
        
    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    //? CREATE new user
    public void create(String redisKey, String payload) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        valueOp.set(redisKey, payload);
    }

    //? READ user data
    //? FIND SPECIFIC USER
    public Optional<String> findUserByEmail(String email) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        String user = valueOp.get(email);
        if (null == user)
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
