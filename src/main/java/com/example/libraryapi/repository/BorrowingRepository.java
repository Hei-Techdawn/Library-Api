package com.example.libraryapi.repository;

import com.example.libraryapi.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {
    List<Borrowing> findAllByBook_Id(Long idBook);
}
