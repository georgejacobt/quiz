package com.georgejacob;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Question> questions;

    public Category(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
