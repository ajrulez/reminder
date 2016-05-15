package com.onecodelabs.reminderexample.test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.onecodelabs.reminderexample.bundle.ReminderBundle;
import com.onecodelabs.reminderexample.remindful.PreferencesRemindfulPersister;
import com.onecodelabs.reminderexample.remindful.RemindfulPersister;
import com.onecodelabs.reminderexample.remindful.SqliteRemindfulPersister;
import com.onecodelabs.reminderexample.test.model.TestObject;
import com.onecodelabs.reminderexample.test.model.TestObjectList;
import com.onecodelabs.reminderexample.test.util.TimeConstants;
import com.onecodelabs.reminderexample.test.util.Timer;

import org.junit.Before;
import org.junit.Test;

public class ReminderPerformanceTest extends AndroidTestCase {

    private static final String TEST_FILE_PREFIX = "test_";
    private static final String BUNDLE_KEY = "Bundle key";
    private static final int[] TEST_RUNS = {100000, 500000};

    private Timer timer;

    private SqliteRemindfulPersister sqliteRemindfulPersister;
    private PreferencesRemindfulPersister preferencesRemindfulPersister;

    @Before
    public void setUp() throws Exception {
        this.timer = new Timer();
        RenamingDelegatingContext context
                = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
        sqliteRemindfulPersister = new SqliteRemindfulPersister(context);
        preferencesRemindfulPersister = PreferencesRemindfulPersister.init(context);
    }

    @Test
    public void testInsertPerformance() {
        for (int objectCountTest : TEST_RUNS) {
            // TODO(gnardini): Fix an issue when saving large number of objects.
            // runSaveAndRetrieve(sqliteRemindfulPersister, objectCountTest, true);
        }
        for (int objectCountTest : TEST_RUNS) {
            runSaveAndRetrieve(preferencesRemindfulPersister, objectCountTest, true);
        }
    }

    private void runSaveAndRetrieve(
            RemindfulPersister persister,
            int objectCount,
            boolean printPartialResults) {
        TestObjectList testObjectList = new TestObjectList();
        for (int i = 0; i < objectCount; i++) {
            testObjectList.addObject(new TestObject("Test Object", i));
        }
        if (printPartialResults) {
            timer.restart();
        }
        ReminderBundle bundle = new ReminderBundle();
        bundle.put(BUNDLE_KEY, testObjectList);
        persister.save(BUNDLE_KEY, bundle);
        if (printPartialResults) {
            System.out.println("Saving " + objectCount + " objects with "
                    + persister.getClass().getSimpleName() + " took "
                    + ((double) timer.getTimeInMillis() / TimeConstants.SECOND_IN_MILLIS)
                    + " seconds");
            timer.restart();
        }
        TestObjectList retrievedObjectList =
                persister.get(BUNDLE_KEY).get(BUNDLE_KEY, TestObjectList.class);
        if (printPartialResults) {
            System.out.println("Retrieving " + retrievedObjectList.size() + " objects with "
                    + persister.getClass().getSimpleName() + " took "
                    + ((double) timer.getTimeInMillis() / TimeConstants.SECOND_IN_MILLIS)
                    + " seconds");
        }
    }

}