package com.example.libraryapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "my_user")
public class MyUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String lastname;
    private String firstname;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    private String role;
}
