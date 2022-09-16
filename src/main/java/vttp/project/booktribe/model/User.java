package vttp.project.booktribe.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User implements Serializable{

    static final long serialVersionUID = 8880220379965714697L;

    @Id
    private String name;
    private String username;
    private String email;
    private String password;
    private String profile;

    public static User createUserProfile(String name, String email, String profile) {
        User userData = new User();

        userData.setName(name);
        userData.setEmail(email);
        userData.setPassword(profile);

        return userData;
    }


    
}

