package com.example.libraryapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int pageNumber;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Long loanNumber;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public enum Status {
        borrowed, available
    }

    @PrePersist
    private void createStatus() {
        this.status = Status.available;
    }
}