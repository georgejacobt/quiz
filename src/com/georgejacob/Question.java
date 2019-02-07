package com.georgejacob;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private int correctAnswer;
    private List<Answer> answers;

    public Question(String question, int correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        answers = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
