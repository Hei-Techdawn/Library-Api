package com.example.libraryapi.service;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Borrowing;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.repository.BorrowingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;

    public List<Borrowing> getAll() {
        return borrowingRepository.findAll();
    }

    public Borrowing getById(Long id) {
        return borrowingRepository.findById(id).get();
    }

    public Borrowing save(Borrowing borrowing) {
        Book book = bookRepository.findById(borrowing.getBook().getId()).get();
        log.info(book.toString());
        if (borrowing.getType().equals(Borrowing.BorrowingType.borrow)) {
            if (book.getStatus().equals(Book.BookStatus.available)) {
                book.setStatus(Book.BookStatus.borrowed);
                bookRepository.save(book);
                Borrowing borrowing1 = borrowingRepository.save(borrowing);
                borrowing1.setBook(book);
                return borrowing1;
            } else if (book.getStatus().equals(Book.BookStatus.borrowed)) {
                log.error("BOOK BORROWED");
                return null;
            }
        } else if (borrowing.getType().equals(Borrowing.BorrowingType.make)) {
            if (book.getStatus().equals(Book.BookStatus.borrowed)) {
                book.setStatus(Book.BookStatus.available);
                bookRepository.save(book);
                Borrowing borrowing1 = borrowingRepository.save(borrowing);
                borrowing1.setBook(book);
                return borrowing1;
            } else if (book.getStatus().equals(Book.BookStatus.available)) {
                log.error("BOOK AVAILABLE");
                return null;
            }
        }
        log.error("BORROWING TYPE INVALID ==> '" + borrowing.getType() + "'");
        return null;
    }
}
