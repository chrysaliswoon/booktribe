package vttp.project.booktribe.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository {
    
    @Value("${quotes.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> template;

    public void save(String author, String payload) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        valueOp.set(author, payload, Duration.ofMinutes(cacheTime));
    }

    public Optional<String> get(String author) {
        ValueOperations<String, String> valueOp = template.opsForValue();
        String value = valueOp.get(author);
        if (null == value)
            return Optional.empty();
        return Optional.of(value);
    }

}
