package com.example.shopping_app.model;

import lombok.Data;

import java.util.List;
@Data
public class Paging<R> {
    int pageNumber = 1;
    int pageSize = 10;
    int totalPages;
    int totalCount;
    List<R> data;
}
