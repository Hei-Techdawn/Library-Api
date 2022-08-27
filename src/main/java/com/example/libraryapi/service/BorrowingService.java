package com.example.libraryapi.service;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Borrowing;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.repository.BorrowingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;

    public List<Borrowing> getAll(String borrowingType) {
        if (borrowingType != null) {
            if (borrowingType.equals("borrow") || borrowingType.equals("make")) {
                return borrow(borrowingRepository.findAll(Sort.by("date").descending()), borrowingType);
            }
        }
        return borrowingRepository.findAll(Sort.by("date").descending());
    }

    public List<Borrowing> getByBookId(Long bookId, String borrowingType) {
        if (borrowingType != null) {
            if (borrowingType.equals("borrow") || borrowingType.equals("make")) {
                return borrow(borrowingRepository.findAllByBook_IdOrderByDateDesc(bookId), borrowingType);
            }
        }
        return borrowingRepository.findAllByBook_IdOrderByDateDesc(bookId);
    }

    public List<Borrowing> borrow(List<Borrowing> borrowingList, String borrowingType) {
        List<Borrowing> borrowings = new ArrayList<>();
        for (Borrowing borrowing : borrowingList) {
            if (String.valueOf(borrowing.getType()).equals(borrowingType)) {
                borrowings.add(borrowing);
            }
        }
        return borrowings;
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
                book.setLoanNumber(book.getLoanNumber() + 1);
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
