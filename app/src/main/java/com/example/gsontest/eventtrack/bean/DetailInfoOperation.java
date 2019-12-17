package com.example.gsontest.eventtrack.bean;

import com.example.gsontest.eventtrack.internal.event.Event;
import com.example.gsontest.eventtrack.internal.record.GsonOperation;

import java.util.ArrayList;
import java.util.List;

public class DetailInfoOperation extends GsonOperation<ArrayList<DetailInfo>> {

    @Override
    protected void operateCustomEvent(ArrayList<DetailInfo> value, Event event) {
        super.operateCustomEvent(value, event);
        final int src = event.mArgInt1;
        final List<String> list = (List<String>) event.getParams();

        if (value != null) {
            final DetailInfo detailInfo = new DetailInfo();
            detailInfo.setSrc(String.valueOf(src));
            detailInfo.setLans(new ArrayList<String>(list));
            value.add(detailInfo);
        }
    }
}
