package com.example.shopping_app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseID<T> extends BaseDate{
    private T id;
}
