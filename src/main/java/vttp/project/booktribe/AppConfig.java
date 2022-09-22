package vttp.project.booktribe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class AppConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    @Value("${spring.redis.username}")
    private String redisUsername;

    @Value("${REDIS_PASSWORD}")
    private String redisPassword;

    @Bean("redis")
    public RedisTemplate<String, String> initRedisTemplate() {
        //? Configure the Redis Database
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);
        redisConfig.setDatabase(redisDatabase);
        redisConfig.setUsername(redisUsername);
        redisConfig.setPassword(redisPassword);

        //? Create instance of Jedis driver
        JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder().build();

        //? Create a factory for Jedis connection
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(redisConfig, jedisConfig);

        jedisFac.afterPropertiesSet();

        //? Create RedisTemplate and configure it
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisFac);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    // @Bean 
    // public JedisConnectionFactory jedisConnectionFactory() {
    //     RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
    //     redisConfig.setHostName(redisHost);
    //     redisConfig.setPort(redisPort);
    //     // redisConfig.setDatabase(redisDatabase);
    //     // redisConfig.setUsername(redisUsername);
    //     // redisConfig.setPassword(redisPassword);

    //     return new JedisConnectionFactory(redisConfig);
    // }

    // @Bean("redis")
    // public RedisTemplate<String, Object> redisTemplate() {
    //     RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setConnectionFactory(jedisConnectionFactory());
    //     redisTemplate.setKeySerializer(new StringRedisSerializer());
    //     redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    //     redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
    //     redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    //     redisTemplate.setEnableTransactionSupport(true);
    //     redisTemplate.afterPropertiesSet();
    //     return redisTemplate;
    // }
}
