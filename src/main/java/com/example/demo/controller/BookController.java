package com.example.demo.controller;


import com.example.demo.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(2L, "1984", "George Orwell"));
    }

    @GetMapping("")
    public List<Book> getBooks(Model model) {
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
    public Book addBook(@RequestBody Book book){
        books.add(book);
        return book;
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = books.stream()
                .filter(b ->b.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        }
        return book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return "Book with Id " + id + " removed";
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String author){
    return books;
    }
}
