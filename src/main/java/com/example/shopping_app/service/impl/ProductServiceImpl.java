package com.example.shopping_app.service.impl;

import com.example.shopping_app.DAO.ProductDAO;
import com.example.shopping_app.dataObject.ProductDO;
import com.example.shopping_app.model.BasePageParam;
import com.example.shopping_app.model.Paging;
import com.example.shopping_app.model.Product;
import com.example.shopping_app.service.ProductService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public Product save(Product product) {
        ProductDO productDO = new ProductDO(product);
        if (productDAO.selectById(product.getId()) == null) {
            int insert = productDAO.insert(product);
            if (insert < 1) {
                return null;
            }
            product.setId(productDO.getUserId());
        } else {
            int update = productDAO.updateStock(productDO);
            if (update < 1) {
                return null;
            }
        }
        return product;
    }
    @Override
    public Paging<Product> pageQueryProduct(BasePageParam param) {
        Paging<Product> result = new Paging<>();
        if (param == null) {
            return null;
        }
        if (param.getPageSize() < 0) {
            param.setPageSize(10);
        }
        if (param.getPageNumber() < 0) {
            param.setPageNumber(0);
        }
        int counts = productDAO.selectAllCounts();
        if (counts <= 0) {
            return result;
        }

        result.setTotalCount(counts);
        result.setPageNumber(param.getPageNumber());
        result.setPageSize(param.getPageSize());
        int totalPage = (int) Math.ceil(counts / param.getPageSize() * 1.0);
        result.setTotalPages(totalPage);

        List<ProductDO> productDOS = productDAO.pageQuery(param);
        List<Product> products = new ArrayList<>();

        if (!CollectionUtils.isEmpty(productDOS)) {
            for (ProductDO productDO : productDOS) {
                products.add(productDO.convertToModel());
            }
        }

        result.setData(products);
        return result;
    }
}
