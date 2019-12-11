package com.example.gsontest.eventtrack;

import android.text.TextUtils;
import android.util.Log;

import com.example.gsontest.eventtrack.internal.CommonParams;
import com.example.gsontest.eventtrack.internal.storage.EventStorage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

final class EventSender extends Thread {

    private final ReentrantLock mSendWait = new ReentrantLock();
    private final Condition mSendCondition = mSendWait.newCondition();

    private final NetReporter mReporter;
    private final EventStorage mStorage;

    EventSender(NetReporter reporter, EventStorage storage) {
        super("EventSender");
        mReporter = reporter;
        mStorage = storage;
    }

    @Override
    public void run() {
        while (true) {
            mSendWait.lock();
            try {
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
                    events = mStorage.getAllEventsFromMainForReport();
                }

//                if (TextUtils.isEmpty(events)) {
//                    continue;
//                }

                final boolean sended = mReporter.sendEvents(events);

                if (!sended) {
                    continue;
                }

                synchronized (CommonParams.sStorageLock) {
                    mStorage.clearMain();
                }

            } finally {
                CommonParams.sIsSending = false;

                EventTrack.getInstance().executeEventsInBackup();
            }
        }
    }

    void report() {
        mSendWait.lock();
        try {
            mSendCondition.signal();
        } finally {
            mSendWait.unlock();
        }
    }
}
