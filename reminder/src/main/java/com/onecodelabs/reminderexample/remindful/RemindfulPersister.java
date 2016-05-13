package com.onecodelabs.reminderexample.remindful;

import com.onecodelabs.reminderexample.bundle.ReminderBundle;

public interface RemindfulPersister {

    void save(String id, ReminderBundle bundle);

    ReminderBundle get(String id);
}
