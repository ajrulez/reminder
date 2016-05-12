package com.onecodelabs.reminder.bundle;

import java.util.HashMap;

public class ReminderBundle {

    private HashMap<String, Object> map;

    public ReminderBundle() {
        map = new HashMap<>();
    }

    public void put(String key, Object object) {
        map.put(key, object);
    }

    public Object get(String key) {
        return map.get(key);
    }
}