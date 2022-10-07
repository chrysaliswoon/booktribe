package vttp.project.booktribe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.project.booktribe.model.Shelf;

@Repository
public class BookRespository {

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    //? Add books to existing user
    public void addBook(String userID, String bookID) {
        HashOperations<String, String, String> hashOp = template.opsForHash();
        hashOp.put(userID, "favourite", bookID.toString());

    

    }
    
}
