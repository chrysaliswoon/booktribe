package vttp.project.booktribe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.BookService;

@Controller
public class GoalsController {

    @Autowired
    BookService bookSvc;

    // ? GOALS PAGE
    @GetMapping(path = "/goals")
    public String getGoalsPage(Model model,HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String bookID = userDetails.getFavourite();
        List<Book> bookDetails = bookSvc.bookDetails(bookID);

        System.out.print(bookDetails);
        
        model.addAttribute("shelf", bookDetails);
        model.addAttribute("userDetails", userDetails);
        return "goals";
    }

}
