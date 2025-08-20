package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController2 {

    private final List<Book> books = new ArrayList<>();

    public BookController2() {
        books.add(new Book(1L, "Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(2L, "1984", "George Orwell"));
        books.add(new Book(3L, "Wiedzmin", "Andrzej Sapkowski"));
        books.add(new Book(4L, "Lord of the Rings", "J.R.R. Tolkien"));
    }

    @GetMapping("/getAvailableBooks")
    public List<Book> listAvailableBooks(@RequestParam("isPresent") Boolean isPresent) {
        if (isPresent) {
            return books.subList(0, 3);
        }
        return books;
    }

}
