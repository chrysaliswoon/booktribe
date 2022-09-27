package vttp.project.booktribe.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import vttp.project.booktribe.service.BookService;
import vttp.project.booktribe.service.QuoteService;
import vttp.project.booktribe.service.UserService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private BookService bookSvc;
    private QuoteService quoteSvc;
    private UserService userSvc;


}
