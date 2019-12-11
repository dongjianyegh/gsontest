package com.example.gsontest.eventtrack.bean;

import com.example.gsontest.eventtrack.internal.event.Event;
import com.example.gsontest.eventtrack.internal.record.GsonOperation;

import java.util.HashMap;
import java.util.Map;

public class GsonLanguageLayout extends GsonOperation<Map<String, Map<String, Integer>>> {

    @Override
    public void operateCustomEvent(Map<String, Map<String, Integer>> value, Event event) {
        final int lanId = event.mArg1;
        final int layoutId = event.mArg2;

        final String key1 = String.valueOf(lanId);
        Map<String, Integer> layoutCount = value.get(key1);
        if (layoutCount == null) {
            layoutCount = new HashMap<>();
            value.put(key1, layoutCount);
        }

        final String key2 = String.valueOf(layoutId);
        final int oldValue = getIntValueFromGson(layoutCount.get(key2));
        layoutCount.put(key2, oldValue + 1);
    }
}
