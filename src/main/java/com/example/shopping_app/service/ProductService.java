package com.example.shopping_app.service;

import com.example.shopping_app.model.BasePageParam;
import com.example.shopping_app.model.Paging;
import com.example.shopping_app.model.Product;

public interface ProductService {
    Product save(Product product);
    Paging<Product> pageQueryProduct(BasePageParam param);
}
