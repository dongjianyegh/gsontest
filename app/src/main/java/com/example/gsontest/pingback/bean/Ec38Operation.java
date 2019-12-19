package com.example.gsontest.pingback.bean;

import com.example.eventtrack.internal.event.Event;
import com.example.eventtrack.internal.record.GsonOperation;

import java.util.ArrayList;
import java.util.HashMap;

public class Ec38Operation extends GsonOperation<HashMap<String, Ec38Item>> {

    public static final int TYPE_ADD_LANGUAGE_CLICK = 0;
    public static final int TYPE_ADD_LANGUAGE_SET_LOG = 1;

    @Override
    protected void operateCustomEvent(HashMap<String, Ec38Item> value, Event event) {
        super.operateCustomEvent(value, event);

        final int customType = event.getOperationType();

        if (value == null) {
            return;
        }


        if (customType == TYPE_ADD_LANGUAGE_CLICK) {
            final int lanId = event.mArgInt1;
            final String lan = String.valueOf(lanId);
            Ec38Item item = value.get(lan);
            if (item == null) {
                item = new Ec38Item();
                value.put(lan, item);
            }

            item.cc++;
        } else if (customType == TYPE_ADD_LANGUAGE_SET_LOG) {
            final int lanId = event.mArgInt1;
            final int from = event.mArgInt2;
            final int to = (Integer) event.mParams;
            final String lan = String.valueOf(lanId);
            Ec38Item item = value.get(lan);
            if (item == null) {
                item = new Ec38Item();
                value.put(lan, item);
                item.cc++;
            }

            item.sc++;

            if (item.sl == null) {
                item.sl = new ArrayList<>();
            }

            if (item.sl.size() > 0) {
                item.sl.add(to);
            } else {
                item.sl.add(from);
                item.sl.add(to);
            }
        }
    }
}
