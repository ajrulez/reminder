package com.onecodelabs.reminder.model;

/**
 * Object that can be used to configure Reminder options at startup (usually in an Application
 * class).
 */
public class ReminderConfig {

    private static final PersistenceMode DEFAULT_PERSISTENCE_MODE =
            PersistenceMode.SHARED_PREFERENCES;

    private final PersistenceMode persistenceMode;

    private ReminderConfig(PersistenceMode persistenceMode) {
        this.persistenceMode = persistenceMode;
    }

    public PersistenceMode getPersistenceMode() {
        return persistenceMode;
    }

    public static ReminderConfig getDefault() {
        return new ReminderConfig(DEFAULT_PERSISTENCE_MODE);
    }

    /**
     * Builder class that creates a {@link ReminderConfig} object with the chosen configuration.
     */
    public static class Builder {

        private PersistenceMode persistenceMode;

        public ReminderConfig build() {
            PersistenceMode persistenceMode = this.persistenceMode == null
                    ? DEFAULT_PERSISTENCE_MODE : this.persistenceMode;
            return new ReminderConfig(persistenceMode);
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
