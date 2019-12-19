package com.example.eventtrack;

import com.example.eventtrack.internal.event.Event;
import com.example.eventtrack.internal.memorycache.MemoryCache;
import com.example.eventtrack.internal.storage.EventStorage;
import com.example.eventtrack.internal.storage.MmkvStorage;

import androidx.annotation.NonNull;

public class EventTrack {

    private final NetReporter mNetReporter;
    private final EventDispatcher mCollector;
    private final EventSender mSender;
    private final EventStorage mStorage;
    private final EventEngine mEngine;
    private final RecordFactory mRecordFactory;

    private static volatile EventTrack sInstance = null;

    public static EventTrack defaultEventTrack() {
        if (sInstance == null) {
            synchronized (EventTrack.class) {
                if (sInstance == null) {
                    sInstance = new EventTrack();
                }
            }
        }

        return sInstance;
    }

    private EventTrack() {
        mNetReporter = new NetReporter() {
            @Override
            public boolean sendEvents(String events) {
                try {
                    Thread.sleep(10000L);
                } catch (Throwable e) {}

                return false;
            }
        }; // TODO
        mStorage = new MmkvStorage();
        mRecordFactory = new RecordFactory();
        final MemoryCache memoryCache = new MemoryCache();
        mEngine = new EventEngine(mStorage, new MemoryCache(), mRecordFactory);
        mCollector = new EventDispatcher(mEngine);
        mSender = new EventSender(mNetReporter, new EventCollector(mStorage, memoryCache, mRecordFactory));

        mCollector.start();
        mSender.start();
    }


    public void trackEvent(@NonNull Event event) {
        mCollector.trackEvent(event);
    }

    public void increaseNumber(String key) {
        mCollector.trackEvent(Event.obtainNumberEvent(key));
    }

    void executeEventsInBackup() {
        mCollector.trackEvent(Event.obtainWakeupEvent());
    }

    public void report() {
        mSender.report();
    }

    public void registerRecordDefine(EventDefine define) {
        mRecordFactory.registRecordDefine(define);
    }

}
