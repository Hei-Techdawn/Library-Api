package com.example.libraryapi.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "borrowing")
public class Borrowing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private BorrowingType type;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @PrePersist
    private void createDate() {
        this.date = new Date();
    }

    public enum BorrowingType {
        borrow, make
    }
}
