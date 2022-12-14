package vttp.project.booktribe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    private UserService userSvc;

    // ? REGISTER PAGE

    @GetMapping(path = "/register")
    public String getRegisterPage() {

        return "register";
    }

    @PostMapping(path = "/")
    public String postRegisterPage(Model model, @RequestBody MultiValueMap<String, String> form) {

        String name = form.getFirst("name");
        String username = form.getFirst("username");
        String email = form.getFirst("email");
        String password = form.getFirst("password");
        String profile = form.getFirst("profile");

        User user = new User(name, username, email, password, profile);

        //? Store data in Redis Database
        userSvc.createProfile(user);

        return "login";
    } 

}
