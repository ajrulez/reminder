package com.onecodelabs.reminderexample.util;

import com.google.gson.Gson;

public class JsonUtils {

    private static final Gson GSON = new Gson();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> a) {
        if (jsonString == null) {
            return null;
        }
        T obj = null;
        try {
            obj = GSON.fromJson(jsonString, a);
        } catch (Exception e) {
        }
        return obj;
    }
}
