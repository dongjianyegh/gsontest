package com.example.gsontest.eventtrack.internal.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({EventType.TYPE_NUMBER, EventType.TYPE_JSON})
@Retention(RetentionPolicy.SOURCE)
public @interface EventType {
    int TYPE_NUMBER = 1;
    int TYPE_JSON = 2;
}
