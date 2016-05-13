package com.onecodelabs.reminderexample.model;

import java.util.List;

public class Question {

    private String question;
    private List<Choice> choices;

    public Question(String question, List<Choice> choices) {
        this.question = question;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
