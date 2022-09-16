package vttp.project.booktribe.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.project.booktribe.model.User;

@Repository
public class UserRepository {
        
    @Autowired
    @Qualifier("redis")
    private RedisTemplate template;

    public static final String HASH_KEY = "User";

    //? CREATE new user
    public User createUser(User user) {
        template.opsForHash().put(HASH_KEY, user.getEmail(), user);
        return user;
    }

    //? READ user data
    public List<User> findAllUser() {
        return template.opsForHash().values(HASH_KEY);
    }

    public User findUserByEmail(String email) {
        return (User) template.opsForHash().get(HASH_KEY, email);
    }

    //? DELETE user data
    public String deleteUser(String email) {
        return "User profile successfully deleted!";
    }
    
}
