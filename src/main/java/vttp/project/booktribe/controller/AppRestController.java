package vttp.project.booktribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
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
    //!!!ERROR - Books that come out are not the correct one!!!
    @GetMapping(path = "/books/{id}") 
    public ResponseEntity<List<Book>> getBooks(@PathVariable String id) {
        List<Book> books = bookSvc.exploreBooks(id);

        if (books.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
            .add("error", "Cannot find book %s".formatted(id))
            .build();
            return (ResponseEntity<List<Book>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(books);
    }

    //? Get specific book 
    //!!!ERROR!!!
    @GetMapping(path = "/book/{id}") 
    public ResponseEntity<String> getBook(@PathVariable String id) {
        List<Book> book = bookSvc.bookDetails(id);

        if (book.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
            .add("error", "Cannot find book %s".formatted(id))
            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(err.toString());
        }

        return ResponseEntity.ok(book.toString());
    }


    //? Get all quotes


    //? Get specific quote



    
}