package vttp.project.booktribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vttp.project.booktribe.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userSvc;

    // ? LOGIN PAGE

    @GetMapping(path = "/")
    public String getLoginPage() {
        return "login";
    }

    // @PostMapping(path = "/")
    // public String postLoginPage() {
    //     return "redirect:/";
    // }

    //? LOGIN --> HOMEPAGE
    @PostMapping(path = "/login")
    public String postHomePage(Model model, @RequestBody MultiValueMap<String, String> form) {
        String email = form.getFirst("email");
        String password = form.getFirst("password");

        //? Check with Redis Database
        userSvc.login(email, password);

        return "redirect:/home";
    }

    //? LOGIN --> REGISTER
    @PostMapping(path = "/register")
    public String postRegisterPage() {
        return "redirect:/register";
    }

}
