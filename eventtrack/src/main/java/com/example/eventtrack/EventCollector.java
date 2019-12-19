package com.example.eventtrack;

import com.example.eventtrack.internal.storage.EventStorage;

public class EventCollector {

    private final EventStorage mStorage;
    private final RecordFactory mRecordFactory;

    EventCollector(EventStorage storage, RecordFactory recordFactory) {
        mStorage = storage;
        mRecordFactory = recordFactory;
    }

    public String getAllEvents() {

        String a = null;

        String c = mRecordFactory.getJsonKeys().toString();

        String b = mRecordFactory.getNumberKeys().toString();

        return null;
    }

    public void clearAllEvents() {
        mStorage.clearMain();
    }
}
