package com.onecodelabs.reminderexample.model;

import android.app.Application;

/**
 * Object that can be used to configure Reminder options at startup (usually in an Application
 * class).
 */
public class ReminderConfig {

    private static final PersistenceMode DEFAULT_PERSISTENCE_MODE =
            PersistenceMode.SHARED_PREFERENCES;

    private final Application application;
    private final PersistenceMode persistenceMode;

    private ReminderConfig(Application application, PersistenceMode persistenceMode) {
        this.application = application;
        this.persistenceMode = persistenceMode;
    }

    public Application getApplication() {
        return application;
    }

    public PersistenceMode getPersistenceMode() {
        return persistenceMode;
    }

    public static ReminderConfig getDefault(Application application) {
        return new ReminderConfig(application, DEFAULT_PERSISTENCE_MODE);
    }

    /**
     * Builder class that creates a {@link ReminderConfig} object with the chosen configuration.
     */
    public static class Builder {

        private Application application;
        private PersistenceMode persistenceMode;

        public Builder(Application application) {
            this.application = application;
        }

        public ReminderConfig build() {
            PersistenceMode persistenceMode = this.persistenceMode == null
                    ? DEFAULT_PERSISTENCE_MODE : this.persistenceMode;
            return new ReminderConfig(application, persistenceMode);
        }

        public Builder setPersistenceMode(PersistenceMode persistenceMode) {
            this.persistenceMode = persistenceMode;
            return this;
        }

        public Builder useSharedPreferences() {
            setPersistenceMode(PersistenceMode.SHARED_PREFERENCES);
            return this;
        }

        public Builder useSqlite() {
            setPersistenceMode(PersistenceMode.SQLITE);
            return this;
        }


    }

}
