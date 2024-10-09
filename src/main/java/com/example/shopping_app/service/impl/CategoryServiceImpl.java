package com.example.shopping_app.service.impl;

import com.example.shopping_app.DAO.CategoryDAO;
import com.example.shopping_app.dataObject.CategoryDO;
import com.example.shopping_app.model.Category;
import com.example.shopping_app.model.Result;
import com.example.shopping_app.service.CategoryService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Category add(Category category) {
        Result<Category> result = new Result<>();
        if (StringUtils.isEmpty(category.getName())) {
            result.setCode("400");
            result.setMessage("Category name cannot be empty");
            return result.getData();
        }
        CategoryDO categoryDO1 = new CategoryDO(category);
        int insert = categoryDAO.insert(categoryDO1);
        if (insert == 1) {
            return category;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryDO> categoryDOS = categoryDAO.selectAll();
        List<Category> categories = new ArrayList<>();
        List<Category> categoryChildren = new ArrayList<>();
        if (CollectionUtils.isEmpty(categoryDOS)) {
            return categories;
        }
        categoryDOS.forEach(categoryDO -> {
            Category category = categoryDO.convertToModel();
            categories.add(category);
        });
        return categories;
    }

    @Override
    public Category get(Long id, boolean deepFill) {
        Category category = categoryDAO.get(id).convertToModel();
        List<CategoryDO> categoryDOS = categoryDAO.selectByParentId(category.getParentCategoryId());
        if (CollectionUtils.isEmpty(categoryDOS)) {
            deepFill = false;
            return null;
        }
        List<Category> categoryChildren = category.getSubCategoryList();
        if (CollectionUtils.isEmpty(categoryChildren)) {
            deepFill = false;
            return null;
        }
        deepFill = true;
        return category;
    }

    @Override
    public List<Category> findSubCategoriesByParentId(Long parentId) {
        if (parentId == null) {
            return null;
        }
        List<CategoryDO> categoryDOS = categoryDAO.selectByParentId(parentId);
        if (CollectionUtils.isEmpty(categoryDOS)) {
            return new ArrayList<>();
        }
        return categoryDOS.stream().map(CategoryDO::convertToModel).toList();
    }
}
