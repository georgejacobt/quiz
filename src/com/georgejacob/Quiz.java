package com.georgejacob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Category> categories;

    public Quiz() {
        categories = new ArrayList<>();
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }

    private void start() {
        try {
            loadQuiz();
        } catch (FileNotFoundException e) {
            System.out.println("Something wet wrong and an exception occurred");
            e.printStackTrace();
            return;
        }
        if (categories.size() > 0) {
            System.out.println("Welcome! Please select a category");
            for (int i=0; i < categories.size(); i++) {
                Category category = categories.get(i);
                System.out.println(i+") "+ category.getName());
            }
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            System.out.println("You selected: "+option);
            playCategory(option);
            // TODO Move 41-47 to a new method called playCategory

        } else {
            System.out.println("This quiz has no categories. Thank you for playing.");
        }

    }

    private void playCategory(int option) {
        Category category = categories.get(option);
        int score = 0;
        int questions = 0;
        for (Question question: category.getQuestions()) {
            questions += 1;
            score = score + askQuestion(question);
        }
        // TODO Change to scored/number of questions
        System.out.println("Your score is: "+ score+"/"+questions);
        // TODO Ask to try again
        System.out.println("Would you like to play again..? Enter 1 to play again and 2 to exit");
        Scanner scanner = new Scanner(System.in);
        int optionPlayAgain = scanner.nextInt();

        if (optionPlayAgain == 1){
            start();
        } else {
            System.out.println("Thank you!");
        }

    }

    private int askQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (int i=0; i < question.getAnswers().size(); i++) {
            Answer answer = question.getAnswers().get(i);
            System.out.println(i+") "+ answer.getText());
        }
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == question.getCorrectAnswer()) {
            return 1;
        } else {
            return 0;
        }
    }

    private void loadQuiz() throws FileNotFoundException {
        if (categories.size() == 0){
            categories.add(loadCategory("res/category1.json"));
            categories.add(loadCategory("res/category2.json"));
            categories.add(loadCategory("res/category3.json"));
        }

    }

    private Category loadCategory(String fileName) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        File jsonFile = new File(fileName);
        if (!jsonFile.exists()) {
            System.out.println("Couldnt find file @"+jsonFile.getAbsolutePath());
        }
        Reader reader = new FileReader(jsonFile);

        Category category = gson.fromJson(reader, Category.class);
        return category;

    }
}
