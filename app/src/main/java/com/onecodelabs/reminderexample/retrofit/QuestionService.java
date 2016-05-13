package com.onecodelabs.reminderexample.retrofit;

import com.onecodelabs.reminderexample.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {

    @GET("/questions")
    Call<List<Question>> getQuestions();
}
