package com.example.gsontest.pingback.bean;


import com.example.eventtrack.internal.event.Event;
import com.example.eventtrack.internal.record.GsonOperation;

import java.util.HashMap;

public class GsonLanguageLayout extends GsonOperation<HashMap<String, HashMap<String, Integer>>> {

    @Override
    public void operateCustomEvent(HashMap<String, HashMap<String, Integer>> value, Event event) {
        final int lanId = event.mArgInt1;
        final int layoutId = event.mArgInt2;

        final String key1 = String.valueOf(lanId);
        HashMap<String, Integer> layoutCount = value.get(key1);
        if (layoutCount == null) {
            layoutCount = new HashMap<>();
            value.put(key1, layoutCount);
        }

        final String key2 = String.valueOf(layoutId);
        final int oldValue = getIntValueFromGson(layoutCount.get(key2));
        layoutCount.put(key2, oldValue + 1);
    }
}
