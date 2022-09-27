package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.model.Quote;
import vttp.project.booktribe.service.BookService;
import vttp.project.booktribe.service.QuoteService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private BookService bookSvc;
    private QuoteService quoteSvc;

    @GetMapping("/books/{id}")
    ResponseEntity<List<Book>> getBooks(@PathVariable String id) {
        List<Book> books = bookSvc.exploreBooks(id);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/quotes")
    ResponseEntity<List<Quote>> getQuotes() {
        List<Quote> quote = quoteSvc.getQuote();
        return ResponseEntity.ok().body(quote);
    }


    
}
