package com.example.eventtrack.internal.event;

import android.os.Parcel;
import android.os.Parcelable;

public final class Event implements Parcelable {
    private static final Object sPoolSync = new Object();
    private static Event sPool;
    private static int sPoolSize = 0;

    private String mKey;
    private @EventType int mEventType; // 支持两种，IntRecord，以及GsonRecord, NoneRecord
    private int mOperationType = Integer.MIN_VALUE;
    private boolean mInPool;
    private Event mNext;

    // 各种参数
    public int mArgInt1;
    public int mArgInt2;
    public String mArgStr1;
    public String mArgStr2;
    public Object mParams;

    private Event() {
    }

    public String getKey() {
        return mKey;
    }

    public @EventType int getEventType() {
        return mEventType;
    }

    public int getNumberIncrease() {
        return mArgInt1;
    }

    public int getOperationType() {
        return mOperationType;
    }

    public Object getParams() {
        return mParams;
    }

    private static final int MAX_POOL_SIZE = 50;

    public static Event obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Event m = sPool;
                sPool = m.mNext;
                m.mNext = null;
                m.mInPool = false;
                sPoolSize--;
                return m;
            }
        }
        return new Event();
    }

    public static Event obtainNumberEvent(final String key, final int increase) {
        Event m = obtain();
        m.mKey = key;
        m.mEventType = EventType.TYPE_NUMBER;
        m.mArgInt1 = increase;
        m.mParams = null;
        return m;
    }

    public static Event obtainNumberEvent(final String key) {
        return obtainNumberEvent(key, 1);
    }

    public static Event obtainJsonEvent(final String key, final int operationType, Object params) {
        Event m = obtain();
        m.mKey = key;
        m.mEventType = EventType.TYPE_JSON;
        m.mOperationType = operationType;
        m.mParams = params;

        return m;
    }

    public static Event obtainJsonEvent(final String key, final int operationType, int arg1, int arg2) {
        Event m = obtain();
        m.mKey = key;
        m.mEventType = EventType.TYPE_JSON;
        m.mOperationType = operationType;
        m.mArgInt1 = arg1;
        m.mArgInt2 = arg2;

        return m;
    }

    public static Event obtainWakeupEvent() {
        Event m = obtain();
        m.mEventType = EventType.TYPE_WAKEUP;
        return m;
    }

    public void recycle() {
        if (mInPool) {
            throw new IllegalStateException("This event cannot be recycled because it "
                    + "is still in pool.");
        }
        mArgInt1 = 0;
        mArgInt2 = 0;
        mInPool = true;
        mOperationType = Integer.MIN_VALUE;
        mParams = null;
        mKey = null;
        mArgStr1 = null;
        mArgStr2 = null;

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                mNext = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR
            = new Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            Event msg = Event.obtain();
            msg.readFromParcel(source);
            return msg;
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mKey);
        dest.writeInt(mEventType);
        dest.writeInt(mOperationType);
        dest.writeInt(mArgInt1);
        dest.writeInt(mArgInt2);
        dest.writeString(mArgStr1);
        dest.writeString(mArgStr2);
        if (mParams != null) {
            try {
                Parcelable p = (Parcelable)mParams;
                dest.writeInt(1);
                dest.writeValue(p);
            } catch (ClassCastException e) {
                throw new RuntimeException(
                        "Can't marshal non-Parcelable objects across processes.");
            }
        } else {
            dest.writeInt(0);
        }
    }

    private void readFromParcel(Parcel source) {
        mKey = source.readString();
        mEventType = source.readInt();
        mOperationType = source.readInt();
        mArgInt1 = source.readInt();
        mArgInt2 = source.readInt();
        mArgStr1 = source.readString();
        mArgStr2 = source.readString();
        if (source.readInt() != 0) {
            mParams = source.readValue(getClass().getClassLoader());
        }
    }
}