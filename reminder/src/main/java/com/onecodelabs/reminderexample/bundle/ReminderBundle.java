package com.onecodelabs.reminderexample.bundle;

import com.onecodelabs.reminderexample.util.JsonUtils;

import java.util.HashMap;

public class ReminderBundle {

    private HashMap<String, String> map;

    /*
    * this variable will first (when the remindable saveSnapshot method is called) hold a timestamp
    * and as soon as onSnapshotAvailable is about to be called, its value is updated to hold
    * the time in milliseconds since this snapshot was saved.
    * */
    private long time;

    public ReminderBundle() {
        map = new HashMap<>();
    }

    public void put(String key, Object object) {
        map.put(key, JsonUtils.toJson(object));
    }

    public <T> T get(String key, Class<T> clazz) {
        return JsonUtils.fromJson(map.get(key), clazz);
    }

    /*
    * returns the time in milliseconds since this snapshot was saved.
    * */
    public long time() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}