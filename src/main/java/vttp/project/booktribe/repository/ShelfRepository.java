package vttp.project.booktribe.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.project.booktribe.model.Bookshelf;

@Repository
public class ShelfRepository {

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    //? Save the book to the bookshelf if it doesn't exist
    public void saveBook(Bookshelf book) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        valueOp.set(book.getBookID(), book.toJson().toString());
    }

    public void saveBook(List<Bookshelf> saveBook) {
    }

    

    

}
