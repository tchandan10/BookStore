package com.Bookstore.Book.controllers;

import com.Bookstore.Book.dto.BookListResponse;
import com.Bookstore.Book.dto.BookPage;
import com.Bookstore.Book.entities.Book;
import com.Bookstore.Book.services.BookService;
import com.Bookstore.Book.services.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Bookstore.Book.dto.BookRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {
    @Autowired
    private BookService bookService;
    private AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest bookRequest) {
        final Map<String, Object> response = new HashMap<>();
        if (authorService.getAuthorForId(bookRequest.getAuthor()) == null) {
            response.put("status", HttpServletResponse.SC_BAD_REQUEST);
            response.put("message", "Country not exist with given -- "+bookRequest.getAuthor());
            return ResponseEntity.badRequest().body(response);
        }
        bookService.saveBookRequest(bookRequest);
        response.put("status", HttpServletResponse.SC_CREATED);
        response.put("message", "Book added successfully");
        //response.put("Book", bookService.saveBookRequest(bookRequest));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<BookListResponse> getAllBookList(BookPage bookPage) {
        BookListResponse bookListResponse = bookService.prepareBookListresponse(bookPage);
        if (bookListResponse.getBookList()== null) {
            logger.warn("No book found, book list is empty. for book list api");
            return ResponseEntity.badRequest().body(BookListResponse.builder()
                    .currentPage(0)
                    .message("Book list is empty!")
                    .totalInPage(0)
                    .build());
        } else {
            logger.info("fetched list of books from database. for book list api");
            logger.info("populating fields in Book list for book list api");
            return ResponseEntity.ok().body(bookListResponse);
        }

    }
}
