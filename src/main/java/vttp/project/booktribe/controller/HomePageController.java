package vttp.project.booktribe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    // ? HOMEPAGE

    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home";
    }

    //? HOMEPAGE --> BOOK
    @PostMapping(path="/search") 
    public String postBookResults() {
        return "redirect:/search";
    }

    // @PostMapping("/home")
    // public String postHomePage(@RequestParam("username") String msg, HttpServletRequest request) {
    //     List<String> msgs = (List<String>) request.getSession().getAttribute("USER_SESSSION");
    //     if (msgs == null) {
    //         msgs = new ArrayList<>();
    //         request.getSession().setAttribute("USER_SESSSION", msgs);
    //     }
    //     msgs.add(msg);
    //     request.getSession().setAttribute("USER_SESSSION", msgs);
    //     System.out.println(msgs);
    //     return "redirect:/home";
    // }

}
