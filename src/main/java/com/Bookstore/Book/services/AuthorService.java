package com.Bookstore.Book.services;

import com.Bookstore.Book.dto.AuthorListResponse;
import com.Bookstore.Book.entities.Author;
import com.Bookstore.Book.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Author getAuthorForId(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public AuthorListResponse propagateAuthor(Author author){
        return AuthorListResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }


    // Other methods...
}

