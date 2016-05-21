package com.onecodelabs.reminderexample;

import com.onecodelabs.reminderexample.bundle.ReminderBundle;

public interface Remindable {

    void saveSnapshot(ReminderBundle snapshot);

    void onSnapshotAvailable(ReminderBundle snapshot);

    void onSnapshotNotFound();
}