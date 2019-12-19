package com.example.eventtrack.internal.storage;

import com.example.eventtrack.internal.event.Event;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

public class MmkvStorage extends EventStorage {
    private static final String MAIN_MMKV_ID = "MMKV_FOREIGN_MAIN_ID";
    private static final String BACK_MMKV_ID = "MMKV_FOREIGN_BACK_ID";

    private final MMKV mMainMmkv;
    private final MMKV mBackMmkv;

    public MmkvStorage() {
        mMainMmkv = MMKV.mmkvWithID(MAIN_MMKV_ID);
        mBackMmkv = MMKV.mmkvWithID(BACK_MMKV_ID);
    }

    @Override
    public int getNumberFromMain(@NonNull String type, int defaultValue) {
        return mMainMmkv.decodeInt(type, defaultValue);
    }

    @Override
    public String getJsonFromMain(@NonNull String type) {
        return mMainMmkv.decodeString(type, null);
    }

    @Override
    public List<Event> getEventFromBackup() {
        String[] keys = mBackMmkv.allKeys();
        if (keys == null || keys.length <= 0) {
            return null;
        }

        // 从小到大排序
        Arrays.sort(keys);

        ArrayList<Event> result = new ArrayList<>(keys.length);
        for (String key : keys) {
            try {
                Event event = mBackMmkv.decodeParcelable(key, Event.class);
                if (event != null) {
                    result.add(event);
                }
            } catch (Throwable ignored) {

            }
        }

        clearBackup();

        return result;
    }

    @Override
    public void saveNumberToMain(@NonNull String type, int value) {
        mMainMmkv.encode(type, value);
    }

    @Override
    public void saveJsonToMain(@NonNull String type, String value) {
        mMainMmkv.encode(type, value);
    }

    @Override
    public String getAllEventsFromMainForReport() {
        return null;
    }

    @Override
    public void saveEventToBackup(@NonNull Event event) {
        final long curTime = System.currentTimeMillis();
        mBackMmkv.encode(String.valueOf(curTime), event);
    }

    @Override
    public void clearBackup() {
        mBackMmkv.clear();
    }

    @Override
    public void clearMain() {
        mMainMmkv.clear();
    }
}
