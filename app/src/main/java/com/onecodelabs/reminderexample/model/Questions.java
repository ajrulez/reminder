package com.onecodelabs.reminderexample.model;

import java.util.List;

public class Questions {
    private List<Question> questions;

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
