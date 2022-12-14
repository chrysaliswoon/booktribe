package vttp.project.booktribe.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
public class HomeController {
    
    @Autowired
    UserService userSvc;

    //? HOMEPAGE
    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String name = userDetails.getName();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("name", name);
        return "home";
    }

}
