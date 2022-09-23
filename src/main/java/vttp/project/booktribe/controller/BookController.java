package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.service.BookServices;

@Controller
public class BookController {

    @Autowired
    private BookServices bookSvc;

    @GetMapping("/search")
    public String getBookResults(Model model, @RequestParam String book) { 
        List<Book> bookResults = bookSvc.exploreBooks(book);
        model.addAttribute("book", book.toUpperCase());
        model.addAttribute("results", bookResults);

        return "explore";
    }

    @GetMapping( path = "/search/{id}")
    public String getBookById(Model model, @PathVariable String id) {
        List<Book> bookDetails = bookSvc.bookDetails(id);
        model.addAttribute("details", bookDetails);
        return "book";
    }
    
}
