package com.example.libraryapi.controller;

import com.example.libraryapi.model.Borrowing;
import com.example.libraryapi.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/borrowing")
@CrossOrigin(origins = "*")
public class BorrowingController {
    private BorrowingService borrowingService;

    @GetMapping(value = "")
    public List<Borrowing> getBorrowing(@RequestParam(name = "type",required = false) String type) {
        return borrowingService.getAll(type);
    }

    @GetMapping(value = "/book/{bookId}")
    public List<Borrowing> getByBookId(
            @PathVariable Long bookId,
            @RequestParam(name = "type",required = false) String type
    ) {
        return borrowingService.getByBookId(bookId,type);
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
