package vttp.project.booktribe.controller;

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
        userSvc.getUsers();

        System.out.print( userSvc.getUsers());

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("users", userSvc.getUsers());
        model.addAttribute("userDetails", userDetails);
        return "tribe";
    }

}
