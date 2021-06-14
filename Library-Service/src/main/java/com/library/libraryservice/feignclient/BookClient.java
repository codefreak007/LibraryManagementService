package com.library.libraryservice.feignclient;

import com.library.libraryservice.repository.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Book-Service", url = "http://localhost:9001")
public interface BookClient {

    @GetMapping(value = "/books")
    public List<Book> getBooks();

    @GetMapping(value = "/books/{id}")
    public Book getBook(@PathVariable Long id);

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book bk);


    @PutMapping(value = "/bookupdate/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bk);


    @DeleteMapping(value = "/bookdelete/{id}")
    public void deleteBook(@PathVariable Long id);


}
