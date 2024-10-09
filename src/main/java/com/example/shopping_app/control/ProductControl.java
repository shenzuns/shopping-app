package com.example.shopping_app.control;

import com.example.shopping_app.DAO.ProductDAO;
import com.example.shopping_app.dataObject.ProductDO;
import com.example.shopping_app.model.Product;
import com.example.shopping_app.model.User;
import com.example.shopping_app.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductControl {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private MongoTemplate mongoTemplate;
    @GetMapping("/product/pub")
    private ResponseEntity<Map<String, Object>> publish(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseData = new HashMap<>();
        if (response.getStatus() != HttpServletResponse.SC_OK) {
            responseData.put("status", "error");
            responseData.put("message", "Unable to publish product");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        request.getSession().getAttribute("publish");
        responseData.put("status", "success");
        responseData.put("message", "Product published successfully");
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("product/pub/api")
    public Map<String, Object> publishAction(@RequestParam("name") String name, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> responseData = new HashMap<>();
        ProductDO productDO = productDAO.selectByName(name);
        if (productDO == null) {
            responseData.put("status", false);
            responseData.put("message", "Product not found");
            return responseData;
        }
        Product product = new Product();
        product.setName(productDO.getName());

        Product savedProduct = productService.save(product);
        if (savedProduct != null || StringUtils.hasText(savedProduct.getId().toString())) {
            responseData.put("status", true);
            responseData.put("message", "Product published successfully");
            return responseData;
        } else {
            responseData.put("status", false);
            responseData.put("message", "Unable to publish product");
            return responseData;
        }
    }
    public List<Product> list(Product productParam) {
        Criteria criteria = new Criteria();
        List<Criteria> subCris = new ArrayList<>();
        if (StringUtils.hasText(productParam.getCategories().toString())) {
            subCris.add(Criteria.where("category").is(productParam.getCategories()));
        }
        if (StringUtils.hasText(productParam.getName())) {
            subCris.add(Criteria.where("name").is(productParam.getName()));
        }
        if (StringUtils.hasText(productParam.getPrice().toString())) {
            subCris.add(Criteria.where("price").is(productParam.getPrice()));
        }

        if (subCris.isEmpty()) {
            return null;
        }

        criteria.andOperator(subCris.toArray(new Criteria[]{}));
        Query query = new Query(criteria);
        query.limit(10);
        return mongoTemplate.find(query,Product.class);
    }
}
