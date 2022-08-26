package com.example.libraryapi.controller;

import com.example.libraryapi.model.Borrowing;
import com.example.libraryapi.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/borrowing")
public class BorrowingController {
    private BorrowingService borrowingService;

    @GetMapping(value = "")
    public List<Borrowing> getBorrowing() {
        return borrowingService.getAll();
    }

    @GetMapping(value = "/book/{bookId}")
    public List<Borrowing> getByBookId(@PathVariable Long bookId) {
        return borrowingService.getByBookId(bookId);
    }

    @GetMapping(value = "/{id}")
    public Borrowing getBorrowingById(@PathVariable Long id) {
        return borrowingService.getById(id);
    }

    @PostMapping(value = "")
    public Borrowing postBorrowing(@RequestBody Borrowing borrowing) {
        return borrowingService.save(borrowing);
    }
}
