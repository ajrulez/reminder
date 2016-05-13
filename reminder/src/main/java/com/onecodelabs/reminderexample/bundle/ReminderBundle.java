package com.onecodelabs.reminderexample.bundle;

import com.onecodelabs.reminderexample.util.JsonUtils;

import java.util.HashMap;

public class ReminderBundle {

    private HashMap<String, String> map;

    public ReminderBundle() {
        map = new HashMap<>();
    }

    public void put(String key, Object object) {
        map.put(key, JsonUtils.toJson(object));
    }

    public <T> T get(String key, Class<T> clazz) {
        return JsonUtils.fromJson(map.get(key), clazz);
    }
}