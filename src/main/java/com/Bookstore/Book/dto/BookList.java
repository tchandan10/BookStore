package com.Bookstore.Book.dto;
import lombok.*;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookList {
    private Long id;
    private String title;
    private String  author;
}
