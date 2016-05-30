package com.onecodelabs.reminder.task;

import android.os.AsyncTask;

import com.onecodelabs.reminder.remindful.RemindfulPersister;

public class DeleteTask extends AsyncTask<Void, Void, Void> {

    private final RemindfulPersister remindfulPersister;

    public DeleteTask(RemindfulPersister remindfulPersister) {
        this.remindfulPersister = remindfulPersister;
    }

    @Override
    protected Void doInBackground(Void... params) {
        remindfulPersister.deleteAll();
        return null;
    }

}
