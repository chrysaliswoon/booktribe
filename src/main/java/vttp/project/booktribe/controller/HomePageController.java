package vttp.project.booktribe.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
public class HomePageController {
    
    @Autowired
    UserService userSvc;

    //? HOMEPAGE
    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "home";
    }


    // ? HOMEPAGE
    @GetMapping(path = "/profile")
    public String getProfilePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "profile";
    }

    //? HOMEPAGE --> BOOK
    @PostMapping(path="/search") 
    public String postBookResults() {
        return "search";
    }

}
