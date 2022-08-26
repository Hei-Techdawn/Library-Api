package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBookByAuthor_Id(Long authorId);
    List<Book> findBookByCategory_Id(Long categoryId);
}
