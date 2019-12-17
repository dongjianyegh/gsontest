package com.example.gsontest.eventtrack;

import com.example.gsontest.eventtrack.internal.event.Event;
import com.example.gsontest.eventtrack.internal.event.JsonOperationType;
import com.example.gsontest.eventtrack.internal.memorycache.MemoryCache;
import com.example.gsontest.eventtrack.internal.storage.EventStorage;
import com.example.gsontest.eventtrack.internal.storage.MmkvStorage;

import java.util.List;

import androidx.annotation.NonNull;

public class EventTrack {

    private final NetReporter mNetReporter;
    private final EventCollector mCollector;
    private final EventSender mSender;
    private final EventStorage mStorage;
    private final EventEngine mEngine;

    private static volatile EventTrack sInstance = null;

    public static EventTrack getInstance() {
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
        mEngine = new EventEngine(new MmkvStorage(), new MemoryCache());
        mCollector = new EventCollector(mEngine);
        mSender = new EventSender(mNetReporter, mStorage);

        mCollector.start();
        mSender.start();
    }


    private void trackEvent(@NonNull Event event) {
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

    public void increaseJson(int lanId, int layoutId) {
        mCollector.trackEvent(Event.obtainJsonEvent(RecordFactory.SETUP_PAGE_SHOW, JsonOperationType.TYPE_CUSTOM_START, lanId, layoutId));
    }

    public void addEc36(int source, List<Integer> lans) {
        Event event = Event.obtainJsonEvent(RecordFactory.DEATIL_INFO_LIST, JsonOperationType.TYPE_CUSTOM_START, lans);
        event.mArgInt1 = source;

        mCollector.trackEvent(event);
    }
}
