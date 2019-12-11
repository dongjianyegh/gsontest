package com.example.gsontest.eventtrack.internal.storage;

import com.example.gsontest.eventtrack.internal.event.Event;

import java.util.List;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;

public abstract class EventStorage {

    @GuardedBy("CommonParams.sStorageLock")
    public abstract int getNumberFromMain(@NonNull String type, int defaultValue);

    public abstract String getJsonFromMain(@NonNull String type);

    @GuardedBy("CommonParams.sStorageLock")
    public abstract List<Event> getEventFromBackup();

    @GuardedBy("CommonParams.sStorageLock")
    public abstract void saveEventToBackup(@NonNull Event event);

    @GuardedBy("CommonParams.sStorageLock")
    public abstract void saveNumberToMain(@NonNull String type, int value);

    @GuardedBy("CommonParams.sStorageLock")
    public abstract void saveJsonToMain(@NonNull String type, String value);

    @GuardedBy("CommonParams.sStorageLock")
    public abstract String getAllEventsFromMainForReport();

    @GuardedBy("CommonParams.sStorageLock")
    public abstract void clearBackup();

    @GuardedBy("CommonParams.sStorageLock")
    public abstract void clearMain();

}
