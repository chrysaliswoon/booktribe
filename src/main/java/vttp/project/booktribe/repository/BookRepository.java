package vttp.project.booktribe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    //? Save the book to the bookshelf
    public void save(String redisKey, String payload) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        valueOp.set(redisKey, payload);
    }

    

}
