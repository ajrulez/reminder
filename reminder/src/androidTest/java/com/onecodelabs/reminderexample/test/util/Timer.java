package com.onecodelabs.reminderexample.test.util;

public class Timer {

    private long startTime;

    public void restart() {
        startTime = System.currentTimeMillis();
    }

    public long getTimeInMillis() {
        return System.currentTimeMillis() - startTime;
    }

}
