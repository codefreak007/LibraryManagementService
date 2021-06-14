package com.example.service;

import com.example.exception.BookNotFoundException;
import com.example.repository.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> getBooks() {
        return repo.findAll();
    }

    public Book getBook(Long id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException("Unable to find the book"));
    }

    public Book addBook(Book bk) {
        return repo.save(bk);
    }

    public Book updateBook(Long id, Book new1) {
        Book old1 = repo.findById(id).orElseThrow(() -> new BookNotFoundException("Doesnt exist.."));
        old1.setId(new1.getId());
        old1.setName(new1.getName());
        return repo.save(old1);
    }


    public void deleteBook(Long id) {
        if (Objects.isNull(repo.findById(id))) {
            throw new BookNotFoundException("Doesnt exist...");
        }
        repo.deleteById(id);
    }

}
