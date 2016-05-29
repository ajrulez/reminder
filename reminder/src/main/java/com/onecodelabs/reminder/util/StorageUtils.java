package com.onecodelabs.reminder.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

public class StorageUtils {

    private static Context sContext;
    private static final Gson GSON = new Gson();

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private StorageUtils(String namePreferences, int mode) {
        if (sContext == null) throw new IllegalStateException("StorageUtils not initialised!");
        if (namePreferences == null || namePreferences.isEmpty()) {
            namePreferences = "storage_utils";
        }
        preferences = sContext.getSharedPreferences(namePreferences, mode);
        editor = preferences.edit();
    }

    public static void init(Context context) {
        StorageUtils.sContext = context;
    }

    public static StorageUtils begin(String namePreferences, int mode) {
        return new StorageUtils(namePreferences, mode);
    }

    public void put(String key, Object object) {
        if (object == null) throw new IllegalArgumentException("can't save null object");
        if (TextUtils.isEmpty(key)) throw new IllegalArgumentException("invalid key");
        editor.putString(key, GSON.toJson(object));
    }

    public <T> T get(String key, Class<T> clazz) {
        String jsonString = preferences.getString(key, null);
        if (jsonString == null) {
            return null;
        }
        try {
            return GSON.fromJson(jsonString, clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object stored with key " + key + " is not an instance of " + clazz.getName());
        }
    }

    public void commit() {
        editor.commit();
    }
}
