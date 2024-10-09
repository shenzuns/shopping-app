package com.example.shopping_app.service;

import com.example.shopping_app.model.Result;
import com.example.shopping_app.model.User;

public interface UserService {
    Result<User> register(User user);

    Result<User> login(String userName, String password);
}
