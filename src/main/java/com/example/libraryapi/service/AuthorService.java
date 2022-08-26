package com.example.libraryapi.service;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).get();
    }

    public Author putById(Long id, Author author) {
        Author oldAuthor = authorRepository.findById(id).get();
        if (author.getLastName() != null) {
            oldAuthor.setLastName(author.getLastName());
        }
        if (author.getFirstName() != null) {
            oldAuthor.setFirstName(author.getFirstName());
        }
        if (author.getPseudo() != null) {
            oldAuthor.setPseudo(author.getPseudo());
        }
        return authorRepository.save(oldAuthor);
    }

    public List<Author> saveAll(List<Author> authorList) {
        return authorRepository.saveAll(authorList);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
