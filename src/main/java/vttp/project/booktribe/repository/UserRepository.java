package vttp.project.booktribe.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


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

    public Optional<String> findUserByEmail(String email) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        String value = valueOp.get(email);
        if (null == value)
            return Optional.empty();
        return Optional.of(value);
    }

    // public static final String HASH_KEY = "User";

    // //? CREATE new user
    // public User createUser(User user) {
    //     template.opsForHash().put(HASH_KEY, user.getEmail(), user);
    //     return user;
    // }

    // //? READ user data
    // public List<User> findAllUser() {
    //     return template.opsForHash().values(HASH_KEY);
    // }

    // public User findUserByEmail(String email) {
    //     return (User) template.opsForHash().get(HASH_KEY, email);
    // }

    // //? DELETE user data
    // public String deleteUser(String email) {
    //     return "User profile successfully deleted!";
    // }
    
}
