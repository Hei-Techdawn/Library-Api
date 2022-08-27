package com.example.libraryapi.service;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public DataFormat<Book> getAll(int page, int size) {
        DataFormat<Book> res = new DataFormat<>();
        if (size > 0 && page > 0) {
            res.setData(bookRepository.findAll(PageRequest.of(page, size, Sort.by("loanNumber").descending())).toList());
            res.setCurrentPage(0);
            res.setLastPage(0);
            return res;
        }
        List<Book> bookList = bookRepository.findAll(Sort.by("loanNumber").descending());
        if (size < 0) {
            res.setData(ManualPagination.getPage(bookList, page));
            res.setLastPage((bookList.size() / 10));
            res.setCurrentPage(page + 1);
        } else {
            res.setData(bookList);
            res.setLastPage(0);
            res.setCurrentPage(0);
        }
        return res;
    }

    public List<Book> getByAuthor(Long authorId) {
        return bookRepository.findBookByAuthor_Id(authorId);
    }

    public List<Book> getByCategory(Long categoryId) {
        return bookRepository.findBookByCategory_Id(categoryId);
    }

    @Transactional
    public List<Book> saveAll(List<Book> bookList) {
        return bookRepository.saveAll(bookList);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book putById(Long id, Book book) {
        Book oldBook = bookRepository.findById(id).get();
        if (book.getTitle() != null) {
            oldBook.setTitle(book.getTitle());
        }
        if (book.getPageNumber() != 0) {
            oldBook.setPageNumber(book.getPageNumber());
        }
        if (book.getAuthor() != null) {
            oldBook.setAuthor(book.getAuthor());
        }
        if (book.getCategory() != null) {
            oldBook.setCategory(book.getCategory());
        }
        return bookRepository.save(oldBook);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
