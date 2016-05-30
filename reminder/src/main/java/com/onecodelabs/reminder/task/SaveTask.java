package com.onecodelabs.reminder.task;

import android.os.AsyncTask;

import com.onecodelabs.reminder.Remindable;
import com.onecodelabs.reminder.Reminder;
import com.onecodelabs.reminder.bundle.ReminderBundle;
import com.onecodelabs.reminder.remindful.RemindfulPersister;

public class SaveTask extends AsyncTask<Void, Void, Void> {

    private Remindable remindable;
    private ReminderBundle snapshot;
    private RemindfulPersister remindfulPersister;

    public SaveTask(Remindable remindable, ReminderBundle snapshot, RemindfulPersister remindfulPersister) {
        this.remindable = remindable;
        this.snapshot = snapshot;
        this.remindfulPersister = remindfulPersister;
    }

    @Override
    protected Void doInBackground(Void... params) {
        remindfulPersister.save(Reminder.id(remindable), snapshot);
        return null;
    }
}
