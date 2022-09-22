package vttp.project.booktribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TribeController {

    // ? TRIBE PAGE

    @GetMapping(path = "/tribe")
    public String getBookTribe() {
        return "tribe";
    }

}
