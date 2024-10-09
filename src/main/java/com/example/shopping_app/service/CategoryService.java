package com.example.shopping_app.service;

import com.example.shopping_app.dataObject.CategoryDO;
import com.example.shopping_app.model.Category;

import java.util.List;

public interface CategoryService {
    //增添一个分类
    Category add(Category category);
    //查询所有分类
    List<Category> findAll();
    //根据id查询单个分类以及其下分类
    Category get(Long id, boolean deepFill);
    //根据父类id查询所有子分类
    List<Category> findSubCategoriesByParentId(Long parentId);
}
