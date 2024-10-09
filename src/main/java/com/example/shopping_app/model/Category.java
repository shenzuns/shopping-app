package com.example.shopping_app.model;

import lombok.Data;

import java.util.List;
@Data
public class Category extends BaseID<Long> {
    String name;
    String description;
    Long parentCategoryId;
    List<Category> subCategoryList;
}
