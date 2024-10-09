package com.example.shopping_app.control;

import com.example.shopping_app.DAO.UserDAO;
import com.example.shopping_app.model.Result;
import com.example.shopping_app.model.User;
import com.example.shopping_app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserControl {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/reg")
    public Result<User> register(@RequestBody User user, HttpServletRequest request,
                                 HttpServletResponse response, HttpSession session) {
        if (userDAO.findByUserName(user.getUserName()) != null) {
            return null;
        }
        Result<User> result = userService.register(user);
        if (result == null) {
            return null;
        }

        result.setSuccess(true);
        if (result.isSuccess()) {
            response.setStatus(500);
            session = request.getSession();
            session.setAttribute("result",result.getData().getId());
        }
        result.setData(user);
        return result;
    }
}
