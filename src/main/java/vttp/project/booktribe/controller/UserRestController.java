package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepo;

    // // ? CREATE new user data
    // @PostMapping
    // public String createUser(@RequestBody User user) {
    //     userRepo.createUser(user);
    //     System.out.println("User details" + user);
    //     return "redirect:/";
    // }

    // @GetMapping("/createUser/success")
    // public ResponseEntity<String> getSuccess() {
    //     return new ResponseEntity<String>("Congratulation! You're part of the Book Tribe now!", HttpStatus.OK);
    // }

    // // ? READ user data
    // @GetMapping
    // public List<User> getAllUsers() {
    //     return userRepo.findAllUser();
    // }

    // @GetMapping("/{email}")
    // public User findUser(@PathVariable String email) {
    //     return userRepo.findUserByEmail(email);
    // }

    // // ? UPDATE user data

    // // ? DELETE user data
    // @DeleteMapping("/{email}")
    // public String deleteUser(@PathVariable String email) {
    //     return userRepo.deleteUser(email);
    // }

}
