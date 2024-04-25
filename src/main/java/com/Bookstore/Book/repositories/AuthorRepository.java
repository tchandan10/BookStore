package com.Bookstore.Book.repositories;
import com.Bookstore.Book.entities.Author;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long Id);
    List<Author> findAuthorByOrderByNameDesc();

    @Override
    List<Author> findAll();
}

