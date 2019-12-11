package com.example.gsontest.eventtrack;

import com.example.gsontest.eventtrack.internal.event.Event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import androidx.annotation.NonNull;


final class EventCollector extends Thread {

    private final BlockingQueue<Event> mQueue;
    private final EventEngine mEngine;

    EventCollector(EventEngine engine) {
        super("TypanyEventCollector");
        mEngine = engine;
        mQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void run() {
        while (true) {
            Event event;
            try {
                event = mQueue.take();
            } catch (InterruptedException e) {
                event = null;
            }

            if (event == null) {
                continue;
            }

            mEngine.process(event);

            event.recycle();
        }
    }

    public void trackEvent(@NonNull Event event) {
        try {
            mQueue.put(event);
        } catch (InterruptedException e) {
        }
    }
}
