package com.Bookstore.Book.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.Bookstore.Book.entities.Book;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    // Getters and setters
}

