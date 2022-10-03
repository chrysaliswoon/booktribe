package vttp.project.booktribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutController {

    @GetMapping(path="/about")
    public String getAboutPage() { 
        return "about2";
    }
}
