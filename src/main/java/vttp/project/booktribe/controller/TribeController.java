package vttp.project.booktribe.controller;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
public class TribeController {

    @Autowired
    private UserService userSvc;

    // ? TRIBE PAGE
    @GetMapping(path = "/tribe")
    public String getBookTribe(Model model, HttpSession session) {
        Optional<Set<String>> users = userSvc.getUsers();

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("users", users.get());
        model.addAttribute("userDetails", userDetails);
        return "tribe";
    }

}
