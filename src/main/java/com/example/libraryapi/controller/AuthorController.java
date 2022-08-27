package com.example.libraryapi.controller;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/author")
@CrossOrigin(origins = "*")
public class AuthorController {
    private AuthorService authorService;

    @GetMapping(value = "")
    public List<Author> getAuthor() {
        return authorService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Author putAuthorById(@PathVariable Long id, @RequestBody Author author) {
        return authorService.putById(id, author);
    }

    @PostMapping(value = "")
    public List<Author> saveAuthor(@RequestBody List<Author> authorList) {
        return authorService.saveAll(authorList);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
