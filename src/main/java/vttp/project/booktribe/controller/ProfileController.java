package vttp.project.booktribe.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.BookService;
import vttp.project.booktribe.service.UserService;

@Controller
public class ProfileController {
    
    @Autowired
    UserService userSvc;

    @Autowired
    BookService bookSvc;

    // ? PROFILE
    @GetMapping(path = "/profile")
    public String getProfilePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String bookID = userDetails.getFavourite();
        // System.out.println("BookID >> " + bookID);
        List<Book> bookDetails = bookSvc.bookDetails(bookID);
        System.out.print(bookDetails);
        
        model.addAttribute("shelf", bookDetails);
        model.addAttribute("userDetails", userDetails);
        return "profile";
    }

    //? PROFILE --> BOOK
    @PostMapping(path="/search") 
    public String postBookResults() {
        return "search";
    }

    //? UPDATE PROFILE
    @PostMapping(path = "/editUser") 
    public String editUser() {
        return "profile";
    }

    //? DELETE PROFILE
    @PostMapping(path = "/deleteUser") 
    public String deleteUser(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String userEmail = userDetails.getEmail();
        userSvc.deleteProfile(userEmail);
        
        return "login";
    }

}
