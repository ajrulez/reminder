package com.onecodelabs.reminder.bundle;

import com.onecodelabs.reminder.util.JsonUtils;

import java.util.HashMap;

public class ReminderBundle {

    private HashMap<String, String> map;

    /*
    * Holds the timestamp of this bundle's creation.
    * It's used to implement isMillisecondsOld()
    * */
    private long createdAt;

    public ReminderBundle() {
        createdAt = System.currentTimeMillis();
        map = new HashMap<>();
    }

    public void put(String key, Object object) {
        map.put(key, JsonUtils.toJson(object));
    }

    public <T> T get(String key, Class<T> clazz) {
        return JsonUtils.fromJson(map.get(key), clazz);
    }

    /*
    * returns true if this snapshot was saved @param:t milliseconds ago (or more).
    * returns false otherwise.
    * */
    public boolean isMillisecondsOld(long t) {
        return t <= (System.currentTimeMillis() - createdAt);
    }
}