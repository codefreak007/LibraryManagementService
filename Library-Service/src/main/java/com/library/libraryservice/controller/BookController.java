package com.library.libraryservice.controller;

import com.library.libraryservice.feignclient.BookClient;
import com.library.libraryservice.repository.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BookController {

    @Autowired
    private BookClient bclient;

    @GetMapping(value = "/books")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(bclient.getBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return new ResponseEntity<Book>(bclient.getBook(id), HttpStatus.OK);
    }

    @PostMapping(value = "/book")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<Book> addBook(@RequestBody Book bk) {
        return new ResponseEntity<Book>(bclient.addBook(bk), HttpStatus.CREATED);
    }

    @PutMapping(value = "/bookupdate/{id}")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bk) {
        return new ResponseEntity<Book>(bclient.updateBook(id, bk), HttpStatus.OK);
    }

    @DeleteMapping(value = "/bookdelete/{id}")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bclient.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    //Fallback Method
    public ResponseEntity<String> defaultResponse(String err) {
        System.out.println("You are seeing this fallback response because the underlying microservice is down.");
        err = "Fallback error as the microservice is down.";
        return new ResponseEntity<String>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
