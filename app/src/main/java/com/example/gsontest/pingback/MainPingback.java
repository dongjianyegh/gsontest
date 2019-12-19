package com.example.gsontest.pingback;


import com.example.eventtrack.EventTrack;
import com.example.eventtrack.internal.event.Event;
import com.example.eventtrack.internal.event.JsonOperationType;

import java.util.List;

public class MainPingback extends MainDefine {

    private MainPingback() {

    }

    public static void increaseJson(int lanId, int layoutId) {
        EventTrack.defaultEventTrack().trackEvent(Event.obtainJsonEvent(EC_1, JsonOperationType.TYPE_CUSTOM_START, lanId, layoutId));
    }

    public static void addEc36(int source, List<Integer> lans) {
        Event event = Event.obtainJsonEvent(EC_3, JsonOperationType.TYPE_CUSTOM_START, lans);
        event.mArgInt1 = source;
        EventTrack.defaultEventTrack().trackEvent(event);
    }
}
