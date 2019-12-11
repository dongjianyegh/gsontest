package com.example.gsontest.eventtrack.internal.memorycache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache {

    private final Map<String, Integer> mMemoryNumber;

    private final Map<String, Object> mMemoryJson;

    public MemoryCache() {
        mMemoryJson = new HashMap<>();
        mMemoryNumber = new HashMap<>();
    }

    public int getMemoryNumber(String key, int defaultValue) {
        final Integer cache = mMemoryNumber.get(key);
        if (cache == null) {
            return defaultValue;
        } else {
            return cache;
        }
    }

    public void putMemoryNumber(String key, int value) {
        mMemoryNumber.put(key, value);
    }

    public Object getMemoryJson(String key) {
        return mMemoryJson.get(key);
    }

    public void putMemoryJson(String key, Object value) {
        mMemoryJson.put(key, value);
    }
}
