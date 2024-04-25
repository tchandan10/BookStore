package com.Bookstore.Book.controllers;

import com.Bookstore.Book.dto.AuthorListResponse;
import com.Bookstore.Book.dto.BookListResponse;
import com.Bookstore.Book.entities.Author;
import com.Bookstore.Book.services.AuthorService;
import com.Bookstore.Book.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @GetMapping("")
    public List<AuthorListResponse> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorListResponse(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }
}



