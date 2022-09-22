package vttp.project.booktribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalsController {

    // ? GOALS PAGE

    @GetMapping(path = "/goals")
    public String getGoalsPage() {
        return "goals";
    }

}
