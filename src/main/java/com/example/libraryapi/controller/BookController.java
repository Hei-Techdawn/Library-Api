package com.example.libraryapi.controller;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/book")
@CrossOrigin(origins = "*")
public class BookController {
    private BookService bookService;

    @GetMapping(value = "")
    public List<Book> getBook(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        return bookService.getAll(page, size);
    }

    @GetMapping(value = "/author/{authorId}")
    public List<Book> getByAuthor(@PathVariable Long authorId) {
        return bookService.getByAuthor(authorId);
    }

    @GetMapping(value = "/category/{categoryId}")
    public List<Book> getByCategory(@PathVariable Long categoryId) {
        return bookService.getByCategory(categoryId);
    }

    @PostMapping(value = "")
    public List<Book> saveBook(@RequestBody List<Book> bookList) {
        return bookService.saveAll(bookList);
    }

    @GetMapping(value = "/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Book putBookById(@PathVariable Long id, @RequestBody Book book) {
        return bookService.putById(id, book);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
