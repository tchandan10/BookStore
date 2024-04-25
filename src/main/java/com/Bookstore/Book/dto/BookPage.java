package com.Bookstore.Book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookPage {
    Integer pageNo;
    Integer pageSize;
    public Integer getPageNo() {
        return (pageNo == null || pageNo == 0 || pageNo == 1) ? 1 : pageNo;
    }
    public Integer getPageSize() {
        return (pageSize == null || pageSize == 0) ? 10 : pageSize;
    }
}
