package com.example.shopping_app.service.impl;

import com.example.shopping_app.DAO.UserDAO;
import com.example.shopping_app.dataObject.UserDO;
import com.example.shopping_app.model.Result;
import com.example.shopping_app.model.User;
import com.example.shopping_app.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        if(StringUtils.isEmpty(user.getUserName())) {
            result.setCode("400");
            result.setMessage("userName cannot be empty");
            return result;
        }
        if (StringUtils.isEmpty(user.getPwd())) {
            result.setCode("400");
            result.setMessage("password cannot be empty");
            return result;
        }
        UserDO userDO = userDAO.findByUserName(user.getUserName());
        if (userDO != null) {
            result.setCode("400");
            result.setMessage("User already exists");
            return result;
        }
        String saltPwd = user.getPwd() + "shopping_app";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toLowerCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(user.getUserName());
        userDO1.setPassword(md5Pwd);

        userDAO.add(userDO1);

        result.setSuccess(true);
        result.setData(userDO1.toModel());
        return result;
    }

    @Override
    public Result<User> login(String userName, String password) {
        Result<User> result = new Result<>();
        if(StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("userName or password cannot be empty");
            return result;
        }
        if(StringUtils.isEmpty(password)) {
            result.setCode("601");
            result.setMessage("userName or password cannot be empty");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);

        if (userDO == null) {
            result.setCode("602");
            result.setMessage("User not found");
            return result;
        }
        String saltPwd = password + "shopping_app";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toLowerCase();

        if (!md5Pwd.equals(userDO.getPassword())) {
            result.setCode("603");
            result.setMessage("Invalid password");
            return result;
        }

        result.setSuccess(true);
        result.setData(userDO.toModel());
        return result;
    }
}
