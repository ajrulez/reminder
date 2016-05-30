package com.onecodelabs.reminder.remindful;

import com.onecodelabs.reminder.bundle.ReminderBundle;

public interface RemindfulPersister {

    void save(String id, ReminderBundle bundle);

    ReminderBundle get(String id);

    void deleteAll();

}
