package com.Bookstore.Book.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookListResponse {
    String message;
    int currentPage;
    int totalInPage;
    long totalBookCount;
    List<BookList> bookList;
}
