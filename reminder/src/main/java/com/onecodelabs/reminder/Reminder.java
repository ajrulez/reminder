package com.onecodelabs.reminder;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.onecodelabs.reminder.bundle.ReminderBundle;
import com.onecodelabs.reminder.callback.MyActivityLifecycleCallbacks;
import com.onecodelabs.reminder.model.ReminderConfig;
import com.onecodelabs.reminder.remindful.PreferencesRemindfulPersister;
import com.onecodelabs.reminder.remindful.RemindfulPersister;
import com.onecodelabs.reminder.remindful.SqliteRemindfulPersister;

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
            @NonNull ReminderConfig config) {
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
        if (remindable == null) return;
        ReminderBundle snapshot = remindfulPersister.get(id(remindable));
        if (snapshot != null) {
            remindable.onSnapshotAvailable(snapshot);
        } else {
            remindable.onSnapshotNotFound();
        }
    }

    public static void save(Remindable remindable) {
        if (remindable == null) return;
        ReminderBundle snapshot = new ReminderBundle();
        remindable.saveSnapshot(snapshot);
        remindfulPersister.save(id(remindable), snapshot);
    }

    private static String id(Remindable remindable) {
        String suffix = "";
        if (remindable instanceof RemindableWithId) {
            RemindableWithId remindableWithId = (RemindableWithId) remindable;
            String remindableId = remindableWithId.remindableId();
            if (!TextUtils.isEmpty(remindableId)) suffix = remindableId;
        }
        return remindable.getClass().getName() + suffix;
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
