package vttp.project.booktribe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.Quote;
import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.QuoteService;

@Controller
public class QuotesController {

    @Autowired
    QuoteService quoteSvc;

    @GetMapping("/inspire")
    public String getQuotes(Model model, HttpSession session) {
        List<Quote> poem = quoteSvc.getPoem();
        List<Quote> quote = quoteSvc.getQuote();

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);

        model.addAttribute("poem", poem);
        model.addAttribute("quote", quote);
        return "inspire";
    }
    
    
}
