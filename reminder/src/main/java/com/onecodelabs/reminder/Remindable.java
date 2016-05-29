package com.onecodelabs.reminder;

import com.onecodelabs.reminder.bundle.ReminderBundle;

public interface Remindable {

    void saveSnapshot(ReminderBundle snapshot);

    void onSnapshotAvailable(ReminderBundle snapshot);

    void onSnapshotNotFound();
}