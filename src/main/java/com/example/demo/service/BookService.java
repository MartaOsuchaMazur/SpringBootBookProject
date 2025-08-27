package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> finAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getAuthor() !=null && book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    public void deleteAll(){
        bookRepository.deleteAll();
    }
}
