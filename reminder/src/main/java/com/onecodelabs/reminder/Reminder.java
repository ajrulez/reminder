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
import com.onecodelabs.reminder.task.DeleteTask;
import com.onecodelabs.reminder.task.RemindTask;
import com.onecodelabs.reminder.task.SaveTask;

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
        new RemindTask(remindable, remindfulPersister).execute();
    }

    public static void save(Remindable remindable) {
        if (remindable == null) return;
        ReminderBundle snapshot = new ReminderBundle();
        remindable.saveSnapshot(snapshot);
        new SaveTask(remindable, snapshot, remindfulPersister).execute();
    }

    public static String id(Remindable remindable) {
        String suffix = "";
        if (remindable instanceof RemindableWithId) {
            RemindableWithId remindableWithId = (RemindableWithId) remindable;
            String remindableId = remindableWithId.remindableId();
            if (!TextUtils.isEmpty(remindableId)) suffix = remindableId;
        }
        return remindable.getClass().getName() + suffix;
    }

    public static void deleteAll() {
        new DeleteTask(remindfulPersister).execute();
    }

    private static class ReminderActivityLifecycleCallbacks extends MyActivityLifecycleCallbacks {
        @Override
        public void onActivityStopped(Activity activity) {
            if (activity instanceof Remindable) {
                Remindable remindable = (Remindable) activity;
                ReminderBundle snapshot = new ReminderBundle();
                remindable.saveSnapshot(snapshot);
                new SaveTask(remindable, snapshot, remindfulPersister).execute();
            }
        }
    }

}
