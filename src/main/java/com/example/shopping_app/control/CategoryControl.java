package com.example.shopping_app.control;

import com.example.shopping_app.DAO.CategoryDAO;
import com.example.shopping_app.model.Category;
import com.example.shopping_app.model.Result;
import com.example.shopping_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryControl {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDAO categoryDAO;
    @PostMapping
    public ResponseEntity<String> addCategory(Category category) {
           try {
               categoryService.add(category);
               return ResponseEntity.ok("Category added successfully");
           }
           catch (Exception e) {
               return ResponseEntity.internalServerError().body("Error while adding category");
           }
    }
    @GetMapping("/allCategory")
    public Result<List<Category>> findAll() {
        Result<List<Category>> result = new Result<>();
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            result.setSuccess(false);
            result.setMessage("No categories found");
            return result;
        }
        result.setData(categories);
        result.setSuccess(true);
        return result;
    }
    @GetMapping("/get/{id}")
    public Result<Category> getCategory(@PathVariable("id") Long id,@RequestParam(name = "deepFill", required = false, defaultValue = "false") boolean deepFill) {
        Result<Category> result = new Result<>();
        Category category = categoryService.get(id, deepFill);

        if (category == null) {
            result.setSuccess(false);
            result.setMessage("Category not found");
            return result;
        }
        if (deepFill) {
            List<Category> subCategories = category.getSubCategoryList();
            if (subCategories != null || subCategories.isEmpty()) {
                subCategories = categoryService.findSubCategoriesByParentId(id);
                category.setSubCategoryList(subCategories);
            }
        }
        result.setData(category);
        result.setSuccess(true);
        return result;
    }
}
