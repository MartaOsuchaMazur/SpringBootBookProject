package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.finAll());
        return "book_list";
    }

    @GetMapping("/add")
    public String showFormToAddBook(Model model){
      model.addAttribute("book", new Book());
      return "add_book";
    }

    @PostMapping ("/add")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showFormToEditBook(@PathVariable Long id, Model model){
        Book book = bookService.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "edit_book";
    }
    @PostMapping("edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @PostMapping("delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/deleteAll")
    public String clearLibrary() {
        bookService.deleteAll();
        return "redirect:/books";
    }
//    @GetMapping("/search")
//    public String searchBooks(@RequestParam String author){
//        bookRepository.findByQuery(author);
//    return "redirect:/books";
//    }
}
