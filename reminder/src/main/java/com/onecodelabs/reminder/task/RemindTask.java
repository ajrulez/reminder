package com.onecodelabs.reminder.task;

import android.os.AsyncTask;

import com.onecodelabs.reminder.Remindable;
import com.onecodelabs.reminder.Reminder;
import com.onecodelabs.reminder.bundle.ReminderBundle;
import com.onecodelabs.reminder.remindful.RemindfulPersister;

public class RemindTask extends AsyncTask<Void, Void, ReminderBundle> {

    private Remindable remindable;
    private RemindfulPersister remindfulPersister;

    public RemindTask(Remindable remindable, RemindfulPersister remindfulPersister) {
        this.remindable = remindable;
        this.remindfulPersister = remindfulPersister;
    }

    @Override
    protected void onPostExecute(ReminderBundle snapshot) {
        if (snapshot != null) {
            remindable.onSnapshotAvailable(snapshot);
        } else {
            remindable.onSnapshotNotFound();
        }
    }

    @Override
    protected ReminderBundle doInBackground(Void... params) {
        return remindfulPersister.get(Reminder.id(remindable));
    }
}
