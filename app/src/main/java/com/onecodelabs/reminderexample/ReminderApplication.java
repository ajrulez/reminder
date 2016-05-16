package com.onecodelabs.reminderexample;

import android.app.Application;

import com.onecodelabs.reminderexample.retrofit.QuestionService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReminderApplication extends Application {

    private static final String URL_APIARY = "http://private-1e85d-remindertestapi.apiary-mock.com";

    private static ReminderApplication instance;

    private QuestionService questionService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Reminder.init(this);
        initRestServices();
    }

    public static ReminderApplication getInstance() {
        return instance;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    private void initRestServices() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_APIARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        questionService = retrofit.create(QuestionService.class);
    }
}
