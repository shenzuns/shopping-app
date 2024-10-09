package com.example.shopping_app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseID<Long>{
    private String userName;
    @JsonSerialize(using = NullSerializer.class)
    @NotNull
    private String pwd;

    private String mobile;

    private String email;

    private String name;

    private String avatar;

    Gender gender;
}
