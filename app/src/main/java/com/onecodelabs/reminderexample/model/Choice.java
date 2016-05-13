package com.onecodelabs.reminderexample.model;

public class Choice {

    private String choice;
    private Integer votes;

    public Choice(String choice, Integer votes) {
        this.choice = choice;
        this.votes = votes;
    }

    public String getChoice() {
        return choice;
    }

    public Integer getVotes() {
        return votes;
    }
}
