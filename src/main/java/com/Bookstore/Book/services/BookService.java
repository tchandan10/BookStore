package com.Bookstore.Book.services;
import com.Bookstore.Book.dto.BookList;
import com.Bookstore.Book.dto.BookListResponse;
import com.Bookstore.Book.dto.BookPage;
import com.Bookstore.Book.entities.Book;
import com.Bookstore.Book.repositories.BookRepository;
import com.Bookstore.Book.services.AuthorService;
import com.Bookstore.Book.dto.BookRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private AuthorService authorService;
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Page<Book> getAllBooks(int pageNo, int perPage) {
        return  bookRepository.findAll(PageRequest.of(pageNo-1, perPage));
    }

    public Book saveBookRequest(BookRequest bookRequest) {
        return bookRepository.save(Book.builder()
                .title(bookRequest.getTitle())
                .author(authorService.getAuthorForId(bookRequest.getAuthor()))
                .build());
    }

    public BookListResponse prepareBookListresponse(BookPage bookPage) {
        Page<Book> bookList = getAllBooks(bookPage.getPageNo(), bookPage.getPageSize());
        return BookListResponse.builder()
                .currentPage(bookPage.getPageNo())
                .bookList(prepareBookList(bookList.toList()))
                .totalInPage(bookPage.getPageSize())
                .totalBookCount(bookList.getTotalElements())
                .build();
    }

    private List<BookList> prepareBookList(List<Book> bookList) {
        List<BookList> bookListdto  = bookList.stream()
                .map(this::populateBookList)
                .collect(Collectors.toList());

        return bookListdto;
    }

    public BookList populateBookList(Book book) {
        return BookList.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor() !=null ? book.getAuthor().getName() : "")
                .build();
    }





}

