package com.telegram.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
    String name;
    String levelEnglish;
    int age;

    @Override
    public String toString() {
        return "Name = " + name + "\n" +
                "Age = " + age + "\n" +
                "English level = " + levelEnglish;
    }
}

