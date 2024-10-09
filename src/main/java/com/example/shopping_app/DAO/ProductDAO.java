package com.example.shopping_app.DAO;

import com.example.shopping_app.dataObject.ProductDO;
import com.example.shopping_app.model.BasePageParam;
import com.example.shopping_app.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductDAO {
    int insert( Product product);
    ProductDO selectByName(String name);
    ProductDO selectById(Long id);
    int updateStock(ProductDO productDO);
    int selectAllCounts();
    List<ProductDO> pageQuery(BasePageParam param);
}
