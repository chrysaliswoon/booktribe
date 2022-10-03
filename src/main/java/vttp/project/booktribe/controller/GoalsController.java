package vttp.project.booktribe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.User;

@Controller
public class GoalsController {

    // ? GOALS PAGE

    @GetMapping(path = "/goals")
    public String getGoalsPage(Model model,HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "goals";
    }

}
