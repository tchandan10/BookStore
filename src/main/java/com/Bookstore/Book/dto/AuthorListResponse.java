package com.Bookstore.Book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class AuthorListResponse {
    private Long id;
    private String name;
}
