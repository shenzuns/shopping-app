package com.example.shopping_app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseID<Long> {
    User user;
    String name;
    String description;
    Double price;
    List<ImageResource> images;
    List<ImageResource> details;
    ProductStatus status;
    List<Category> categories;
    Integer stock;
}
