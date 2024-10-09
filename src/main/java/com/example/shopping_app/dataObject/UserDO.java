package com.example.shopping_app.dataObject;

import com.example.shopping_app.model.Gender;
import com.example.shopping_app.model.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data

public class UserDO {
    private long id;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;
    private String userName;
    private String email;
    private String password;
    private String mobile;
    private String avatar;
    private String gender;

    public User toModel() {
        User user = new User();
        user.setId(getId());
        user.setGmtCreated(getGmtCreated());
        user.setGmtModified(getGmtModified());
        user.setUserName(getUserName());
        user.setEmail(getEmail());
        user.setPwd(getPassword());
        user.setMobile(getMobile());
        user.setAvatar(getAvatar());
        user.setGender(Gender.valueOf(getGender()));
        return user;
    }
}
