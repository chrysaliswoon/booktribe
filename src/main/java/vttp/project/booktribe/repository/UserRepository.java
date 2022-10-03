package vttp.project.booktribe.repository;

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
    //? FIND USER
    public Optional<String> findUserByEmail(String email) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        String value = valueOp.get(email);
        if (null == value)
            return Optional.empty();
        return Optional.of(value);
    }

    public Optional<Set<String>> findAllUsers() {
        Set<String> allKeys = template.keys("*");
        return Optional.of(allKeys);
    }
    
}
