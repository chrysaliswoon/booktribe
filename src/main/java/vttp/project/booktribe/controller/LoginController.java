package vttp.project.booktribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // ? LOGIN PAGE

    @GetMapping(path = "/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping(path = "/")
    public String postLoginPage() {
        return "redirect:/";
    }

    //? LOGIN --> HOMEPAGE
    @PostMapping(path = "/home")
    public String postHomePage() {
        return "redirect:/home";
    }

    //? LOGIN --> REGISTER
    @PostMapping(path = "/register")
    public String postRegisterPage() {
        return "redirect:/register";
    }

}
