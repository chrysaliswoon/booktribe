package vttp.project.booktribe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    // ? LOGOUT PAGE
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userDetails");
        session.invalidate();
        System.out.println("User has been logged out");
        return "/login";
    }

}
