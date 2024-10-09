package com.example.shopping_app.DAO;

import com.example.shopping_app.dataObject.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CategoryDAO {
    int insert(CategoryDO categoryDO);
    int update(CategoryDO categoryDO);
    int delete(@Param("id") Long id);
    List<CategoryDO> selectAll();
    List<CategoryDO> selectByParentIds(@Param("parentIds") List<Long> ids);
    List<CategoryDO> selectByParentId(Long id);
    CategoryDO get(Long id);
}
