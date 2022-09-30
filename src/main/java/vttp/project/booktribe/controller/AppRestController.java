package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import vttp.project.booktribe.model.Book;
import vttp.project.booktribe.model.Quote;
import vttp.project.booktribe.service.BookService;
import vttp.project.booktribe.service.QuoteService;
import vttp.project.booktribe.service.UserService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppRestController {

    @Autowired
    private BookService bookSvc;
    private QuoteService quoteSvc;

    //? Get all books
    @GetMapping(path = "{id}") 
    public ResponseEntity<String> getBooks(@PathVariable String id) {
        List<Book> books = bookSvc.exploreBooks(id);

        if (books.isEmpty()) {
        }


        return null;
    }

    //? Get specific book


    //? Get all quotes


    //? Get specific quote



    // @GetMapping("/book/{id}")
    // ResponseEntity<List<Book>> getBook(@PathVariable String id) {
    //     List<Book> book = bookSvc.bookDetails(id);
    //     return ResponseEntity.ok().body(book);
    // }

    // @GetMapping("/quotes")
    // ResponseEntity<List<Quote>> getQuotes() {
    //     List<Quote> quote = quoteSvc.getQuote();
    //     return ResponseEntity.ok().body(quote);
    // }


    
}
