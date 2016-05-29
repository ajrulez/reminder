package com.onecodelabs.reminder.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class that reads and writes bundles to an SQLite database. If a database doesn't exist, it
 * creates it and a table where to store key-values.
 */
public class SqlBundleHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OneCodeLabs_Reminder";
    private static final int DATABASE_VERSION = 1;

    // Strings that represent names or commands used to create and update the database.
    private static final String TABLE_BUNDLES = "OneCodeLabs_Reminder_Bundles";
    private static final String KEY_ID = "id";
    private static final String KEY_BUNDLE = "bundle";
    private static final String TABLE_BUNDLES_CREATE =
            "CREATE TABLE " + TABLE_BUNDLES + "("
                    + KEY_ID + " TEXT PRIMARY KEY,"
                    + KEY_BUNDLE + " TEXT"
                    + ")";
    private static final String TABLE_BUNDLES_DROP = "DROP TABLE IF EXISTS " + TABLE_BUNDLES;

    private static final int KEY_BUNDLE_INDEX = 1;

    public SqlBundleHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BUNDLES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If there's a version upgrade, destroy the table and create a new one.
        db.execSQL(TABLE_BUNDLES_DROP);
        onCreate(db);
    }

    /**
     * Writes a row with values |id|, |bundle| to the database.
     */
    public void write(String id, String bundle) {
        // Get writeable DB instance.
        SQLiteDatabase db = this.getWritableDatabase();
        // Write key-value pairs.
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_BUNDLE, bundle);
        // Insert row.
        db.replace(TABLE_BUNDLES, null, values);
        // Closing database connection.
        db.close();
    }

    /**
     * Reads the row with |id| and returns the bundle associated with it if it exists.
     */
    public String read(String id) {
        // Get readable DB instance.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_BUNDLES, // Table name.
                new String[]{KEY_ID, KEY_BUNDLE}, // Columns.
                KEY_ID + "=?", // Selection.
                new String[]{id}, // Selection args.
                null, null, null, null); // Other parameters that can be null.
        String bundle = null;
        if (cursor != null) {
            cursor.moveToFirst();
            // Don't try to read from the cursor if no rows were found.
            if (cursor.getCount() > 0) {
                bundle = cursor.getString(KEY_BUNDLE_INDEX);
            }
            cursor.close();
        }
        return bundle;
    }

}
