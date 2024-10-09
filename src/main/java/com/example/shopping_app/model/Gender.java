package com.example.shopping_app.model;

public enum Gender {
    male("男"),
    female("女"),
    unknown("未知");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
