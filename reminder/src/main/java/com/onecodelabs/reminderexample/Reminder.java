package com.onecodelabs.reminderexample;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import com.onecodelabs.reminderexample.bundle.ReminderBundle;
import com.onecodelabs.reminderexample.callback.MyActivityLifecycleCallbacks;
import com.onecodelabs.reminderexample.model.ReminderConfig;
import com.onecodelabs.reminderexample.remindful.PreferencesRemindfulPersister;
import com.onecodelabs.reminderexample.remindful.RemindfulPersister;
import com.onecodelabs.reminderexample.remindful.SqliteRemindfulPersister;

public class Reminder {

    public static final String TAG = "Reminder";

    private static RemindfulPersister remindfulPersister;

    public static void init(Application application) {
        init(application, ReminderConfig.getDefault());
    }

    public static void init(Application application, @NonNull ReminderConfig config) {
        initRemindfulPersister(application, config);
        application.registerActivityLifecycleCallbacks(new ReminderActivityLifecycleCallbacks());
    }

    private static void initRemindfulPersister(
            Application application,
            @NonNull  ReminderConfig config) {
        switch (config.getPersistenceMode()) {
            case SHARED_PREFERENCES:
                remindfulPersister = PreferencesRemindfulPersister.init(application);
                break;
            case SQLITE:
                remindfulPersister = new SqliteRemindfulPersister(application);
            break;
        }
    }

    public static void remind(Remindable remindable) {
        ReminderBundle snapshot = remindfulPersister.get(id(remindable));
        if (snapshot != null) remindable.onSnapshotAvailable(snapshot);
    }

    public static void save(Remindable remindable) {
        ReminderBundle snapshot = new ReminderBundle();
        remindable.saveSnapshot(snapshot);
        remindfulPersister.save(id(remindable), snapshot);
    }

    private static String id(Remindable remindable) {
        return remindable.getClass().getName();
    }

    private static class ReminderActivityLifecycleCallbacks extends MyActivityLifecycleCallbacks {
        @Override
        public void onActivityStopped(Activity activity) {
            if (activity instanceof Remindable) {
                Remindable remindable = (Remindable) activity;
                ReminderBundle snapshot = new ReminderBundle();
                remindable.saveSnapshot(snapshot);
                remindfulPersister.save(id(remindable), snapshot);
            }
        }
    }

}
