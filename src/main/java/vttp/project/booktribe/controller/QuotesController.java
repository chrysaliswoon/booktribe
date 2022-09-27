package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.project.booktribe.model.Quote;
import vttp.project.booktribe.service.QuoteService;

@Controller
public class QuotesController {

    @Autowired
    QuoteService quoteSvc;

    @GetMapping("/inspire")
    public String getQuotes(Model model) {
        List<Quote> quote = quoteSvc.getQuote();

        model.addAttribute("quote", quote);
        return "inspire";
    }
    
}
