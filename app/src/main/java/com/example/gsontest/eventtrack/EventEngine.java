package com.example.gsontest.eventtrack;

import com.example.gsontest.eventtrack.internal.CommonParams;
import com.example.gsontest.eventtrack.internal.event.Event;
import com.example.gsontest.eventtrack.internal.event.EventType;
import com.example.gsontest.eventtrack.internal.memorycache.MemoryCache;
import com.example.gsontest.eventtrack.internal.record.GsonRecord;
import com.example.gsontest.eventtrack.internal.storage.EventStorage;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.NonNull;

public class EventEngine {
    private Gson mGson = new Gson();

    private final EventStorage mStorage;
    private final MemoryCache mMemory;
    // 初始化为true，第一次执行指令的时候，会从备份存储加载所有指令，然后执行，后续所有的走正常逻辑
    private boolean mHasEventInBackup = true;

    EventEngine(final EventStorage storage, final MemoryCache memory) {
        mStorage = storage;
        mMemory = memory;
    }


    void process(@NonNull Event event) {

        synchronized (CommonParams.sStorageLock) {
            if (CommonParams.sIsSending) {
                mStorage.saveEventToBackup(event);
                mHasEventInBackup = true;
            } else {
                processEventsInBackup();
                processInternal(event);
            }
        }
    }

    private void processInternal(@NonNull Event event) {
        // 从内存当中取值
        @EventType int type = event.getEventType();

        if (type == EventType.TYPE_NUMBER) {
            processNumber(event.getKey(), event.getNumberIncrease());
        } else if (type == EventType.TYPE_JSON) {
            processJson(event.getKey(), event);
        } else if (type == EventType.TYPE_WAKEUP) {
            processEventsInBackup();
        }
    }

    private void processEventsInBackup() {
        if (mHasEventInBackup) {
            final List<Event> events = mStorage.getEventFromBackup();
            if (events != null) {
                for (Event eventBackup : events) {
                    processInternal(eventBackup);
                }
            }
            mHasEventInBackup = false;

            mStorage.clearBackup();
        }
    }

    private void processNumber(String key, int increase) {
        int cache = mMemory.getMemoryNumber(key, CommonParams.INVALIDE_NUMBER_VALUE);
        if (cache == CommonParams.INVALIDE_NUMBER_VALUE) {
            cache = mStorage.getNumberFromMain(key, CommonParams.INVALIDE_NUMBER_VALUE);
        }

        if (cache == CommonParams.INVALIDE_NUMBER_VALUE) {
            cache += increase;
            // 理乱上不会溢出，溢出了做个特殊处理吧
            if (cache < 0) {
                cache = Integer.MAX_VALUE;
            }
        }

        mMemory.putMemoryNumber(key, cache);

        try {
            mStorage.saveNumberToMain(key, cache);
        } catch (Throwable ignored) {

        }
    }

    @SuppressWarnings("unchecked")
    private void processJson(String key, Event event) {
        GsonRecord gsonRecord = RecordFactory.getRecord(key);

        //
        Class<?> clazz = gsonRecord.getClazz();
        Type type = gsonRecord.getType();

        Object object = mMemory.getMemoryJson(key);

        if (object == null) {
            // 缓存没有，需要从main里面去读
            final String json = mStorage.getJsonFromMain(key);

            try {
                object = gsonRecord.getOperation().deserialize(mGson, type, json);
            } catch (Throwable ignored) {

            }
        }

        if (object == null) {
            try {
                object = clazz.newInstance();
                mMemory.putMemoryJson(key, object);
            } catch (Throwable ignored) {
                object = null;
            }
        }

        if (object == null) {
            return;
        }

        mMemory.putMemoryJson(key, object);

        gsonRecord.getOperation().operateEvent(object, event);

        try {
            final String result = gsonRecord.getOperation().serialize(mGson, clazz, object);
            mStorage.saveJsonToMain(key, result);
        } catch (Throwable e) {

        }
    }
}
