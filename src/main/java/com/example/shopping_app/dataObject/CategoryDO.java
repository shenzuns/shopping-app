package com.example.shopping_app.dataObject;

import com.example.shopping_app.model.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
@Data
public class CategoryDO {
    long id;
    LocalDateTime gmtCreated;
    LocalDateTime gmtModified;
    String name;
    String description;
    Long parentCategoryId;

    public CategoryDO(Category category) {
        BeanUtils.copyProperties(category, this);
        if(category.getId()!= null) {
            this.id = category.getId();
        }
    }
    public Category convertToModel() {
        Category category = new Category();
        BeanUtils.copyProperties(this, category);
        if(!StringUtils.isEmpty(this.getId())) {
            category.setId(this.id);
        }
        return category;
    }
}
