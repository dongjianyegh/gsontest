package com.example.eventtrack;

import android.util.Log;

import com.example.eventtrack.internal.CommonParams;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

final class EventSender extends Thread {

    private final ReentrantLock mSendWait = new ReentrantLock();
    private final Condition mSendCondition = mSendWait.newCondition();

    private final NetReporter mReporter;
    private final EventCollector mCollector;


    EventSender(NetReporter reporter, EventCollector collector) {
        super("EventSender");
        mReporter = reporter;
        mCollector = collector;
    }

    @Override
    public void run() {
        while (true) {
            mSendWait.lock();
            try {
                Log.d("EventSender", "wait report");
                mSendCondition.await();
                Log.d("EventSender", "start report");
            } catch (InterruptedException e) {

            } finally {
              mSendWait.unlock();
            }

            try {


                CommonParams.sIsSending = true;

                String events;
                synchronized (CommonParams.sStorageLock) {
                    events = mCollector.getAllEvents();
                }

//                if (TextUtils.isEmpty(events)) {
//                    continue;
//                }

                final boolean sended = mReporter.sendEvents(events);

                if (!sended) {
                    continue;
                }

                synchronized (CommonParams.sStorageLock) {
                    mCollector.clearAllEvents();
                }

            } finally {
                CommonParams.sIsSending = false;

                EventTrack.defaultEventTrack().executeEventsInBackup();
            }
        }
    }

    void report() {
        mSendWait.lock();
        try {
            mSendCondition.signalAll();
        } finally {
            mSendWait.unlock();
        }
    }
}
