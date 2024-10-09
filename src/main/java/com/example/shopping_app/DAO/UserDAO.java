package com.example.shopping_app.DAO;

import com.example.shopping_app.dataObject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper

public interface UserDAO {
    int batchAdd(@Param("list") List<UserDO> userDOs);

    List<UserDO> findByIds(@Param("ids") List<Long> ids);

    int add(UserDO userDO);

    int update(UserDO userDO);

    int delete(long id);

    UserDO findById(@Param("id") long id);

    UserDO findByUserName(@Param("userName") String name);
}
