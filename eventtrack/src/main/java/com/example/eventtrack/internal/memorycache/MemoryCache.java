package com.example.eventtrack.internal.memorycache;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.GuardedBy;

public class MemoryCache {

    private final Map<String, Integer> mMemoryNumber;

    private final Map<String, Object> mMemoryJson;

    public MemoryCache() {
        mMemoryJson = new HashMap<>();
        mMemoryNumber = new HashMap<>();
    }

    @GuardedBy("CommonParams.sStorageLock")
    public int getMemoryNumber(String key, int defaultValue) {
        final Integer cache = mMemoryNumber.get(key);
        if (cache == null) {
            return defaultValue;
        } else {
            return cache;
        }
    }

    @GuardedBy("CommonParams.sStorageLock")
    public void putMemoryNumber(String key, int value) {
        mMemoryNumber.put(key, value);
    }

    @GuardedBy("CommonParams.sStorageLock")
    public Object getMemoryJson(String key) {
        return mMemoryJson.get(key);
    }

    @GuardedBy("CommonParams.sStorageLock")
    public void putMemoryJson(String key, Object value) {
        mMemoryJson.put(key, value);
    }

    @GuardedBy("CommonParams.sStorageLock")
    public void clear() {
        mMemoryJson.clear();
        mMemoryNumber.clear();
    }
}
