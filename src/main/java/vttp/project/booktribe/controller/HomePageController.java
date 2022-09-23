package vttp.project.booktribe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
public class HomePageController {
    
    @Autowired
    UserService userSvc;

    //? HOMEPAGE
    @GetMapping("/home")
    public String getHomePage(Model model) {

        List<User> userProfile = userSvc.userProfile();
        model.addAttribute("profile", userProfile);

        return "home";
    }

    // ? HOMEPAGE
    @GetMapping(path = "/profile")
    public String getProfilePage() {
        return "profile";
    }

    //? HOMEPAGE --> BOOK
    @PostMapping(path="/search") 
    public String postBookResults() {
        return "redirect:/search";
    }

}
