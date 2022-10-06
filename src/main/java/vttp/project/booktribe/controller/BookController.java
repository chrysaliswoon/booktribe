package vttp.project.booktribe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.model.Shelf;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookSvc;

    @GetMapping(path = "/search")
    public String getBookResults(Model model, @RequestParam String book, HttpSession session) { 
        List<Book> bookResults = bookSvc.exploreBooks(book);
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("book", book.toUpperCase());
        model.addAttribute("results", bookResults);

        return "explore";
    }

    @GetMapping( path="/search/{id}")
    public String getBookById(Model model, @PathVariable String id, HttpSession session) {
        List<Book> bookDetails = bookSvc.bookDetails(id);
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("details", bookDetails);
        return "book";
    }


    @PostMapping(path = "/favourite")
    public String favouriteBook(Model model, HttpSession session, @RequestBody MultiValueMap<String, String> form) {
        //? When user click on Favourite button it will get the current book ID
        String bookID = form.getFirst("favourite");
        List<Shelf> addBook = bookSvc.addBook(bookID);
        System.out.print(addBook);

        
        //? Gets the existing user details through session
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "profile";
    }
 
}
