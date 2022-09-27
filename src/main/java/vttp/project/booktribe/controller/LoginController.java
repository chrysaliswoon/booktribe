package vttp.project.booktribe.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

import javax.swing.JOptionPane;

@Controller
public class LoginController {

    @Autowired
    private UserService userSvc;

    // ? LOGIN PAGE
    @GetMapping(path = {"/", "/login"})
    public String getLoginPage() {
        return "login";
    }


    @GetMapping(path = "/errorLogin")
    public String errorLogin() {
        String errorLogin = "Incorrect email or password";
        System.out.print(errorLogin);
        JOptionPane.showMessageDialog(null, errorLogin);
        return "login";
    }


    //? LOGIN --> HOMEPAGE
    @PostMapping(path = "/login")
    public String postHomePage(Model model, @RequestBody MultiValueMap<String, String> form, HttpSession session) {
        String email = form.getFirst("email");
        String password = form.getFirst("password");

        //? Check with Redis Database
        Boolean loginStatus = userSvc.login(email, password);

        //? If it is wrong, then inform the user that the username and password is incorrect
        if (loginStatus == false) {
            return "login";
        }
        
        //? If it is correct, store in the session and go to the homepage
        User userDetails = userSvc.userDetails(email);
        
        session.setAttribute("userDetails", userDetails);
        model.addAttribute("userDetails", userDetails);
        return "home";
    }

    //? LOGIN --> REGISTER
    @PostMapping(path = "/register")
    public String postRegisterPage() {
        return "register";
    }

}
