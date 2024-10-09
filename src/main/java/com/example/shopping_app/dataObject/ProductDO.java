package com.example.shopping_app.dataObject;

import com.example.shopping_app.model.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProductDO {
    long userId;
    String name;
    String description;
    Integer stock;
    String images;
    String status;
    String details;
    Double price;
    String categories;

    public ProductDO(Product product) {
        BeanUtils.copyProperties(product, this);
    }
    public Product convertToModel() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }
}
