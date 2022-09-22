package vttp.project.booktribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @GetMapping("/search")
    public String getBookResults(Model model, @RequestParam String book) { 
        // List<Book> bookResults = bookSvc.exploreBooks(book);
        model.addAttribute("book", book);
        // model.addAttribute("results", bookResults);
        return "book";
    }
    
}
