package com.example.shopping_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result<D> {
    @JsonProperty("isSuccess")
    boolean success = false;

    String code;

    String message;

    D data;

    public <T> Result<T> create() {
        return null;
    }
}
