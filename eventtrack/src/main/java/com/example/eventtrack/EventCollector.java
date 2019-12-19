package com.example.eventtrack;

import com.example.eventtrack.internal.memorycache.MemoryCache;
import com.example.eventtrack.internal.storage.EventStorage;

public class EventCollector {

    private final EventStorage mStorage;
    private final MemoryCache mMemoryCache;
    private final RecordFactory mRecordFactory;

    EventCollector(EventStorage storage, MemoryCache memoryCache, RecordFactory recordFactory) {
        mStorage = storage;
        mMemoryCache = memoryCache;
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
        mMemoryCache.clear();
    }
}
