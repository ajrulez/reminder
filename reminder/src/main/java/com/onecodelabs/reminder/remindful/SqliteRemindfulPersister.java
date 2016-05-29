package com.onecodelabs.reminder.remindful;

import android.content.Context;

import com.google.gson.Gson;
import com.onecodelabs.reminder.bundle.ReminderBundle;
import com.onecodelabs.reminder.helper.SqlBundleHelper;

/**
 * Implementation of {@link RemindfulPersister} that saves and retrieves bundles from a SQLite DB.
 */
public class SqliteRemindfulPersister implements RemindfulPersister {

    private static final Gson GSON = new Gson();

    private final SqlBundleHelper sqlBundleHelper;

    public SqliteRemindfulPersister(Context context) {
        sqlBundleHelper = new SqlBundleHelper(context);
    }

    @Override
    public void save(String id, ReminderBundle bundle) {
        sqlBundleHelper.write(id, GSON.toJson(bundle));
    }

    @Override
    public ReminderBundle get(String id) {
        // Get the bundle saved as string from the database.
        String bundle = sqlBundleHelper.read(id);
        if (bundle == null) {
            return null;
        }
        return GSON.fromJson(bundle, ReminderBundle.class);
    }

}
