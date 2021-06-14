package com.example.controller;

import com.example.repository.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(service.getBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return new ResponseEntity<Book>(service.getBook(id), HttpStatus.OK);
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Book> addBook(@RequestBody Book bk) {
        return new ResponseEntity<Book>(service.addBook(bk), HttpStatus.CREATED);
    }

    @PutMapping(value = "/bookupdate/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bk) {
        return new ResponseEntity<Book>(service.updateBook(id, bk), HttpStatus.OK);
    }

    @DeleteMapping(value = "/bookdelete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
