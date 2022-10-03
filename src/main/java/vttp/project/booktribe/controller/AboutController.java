package vttp.project.booktribe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.User;


@Controller
public class AboutController {

    @GetMapping(path="/about")
    public String getAboutPage(Model model,HttpSession session) { 
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "about";
    }
}
