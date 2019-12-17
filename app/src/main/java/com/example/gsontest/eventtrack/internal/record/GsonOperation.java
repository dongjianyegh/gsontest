package com.example.gsontest.eventtrack.internal.record;

import com.example.gsontest.eventtrack.internal.event.Event;
import com.example.gsontest.eventtrack.internal.event.JsonOperationType;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonOperation<T> {

    public final String serialize(Gson gson, Type type, Object value) {
        try {
            return gson.toJson(value, type);
        } catch (Throwable e) {
            return null;
        }
    }

    public final Object deserialize(Gson gson, Type type, String value) {
        try {
            return gson.fromJson(value, type);
        } catch (Throwable e) {
            return null;
        }
    }

    public static final int getIntValueFromGson(Object value) {
        if (value instanceof Double) {
            return ((Double) value).intValue();
        } else if (value instanceof  Integer) {
            return (int) value;
        } else {
            return 0;
        }
    }

    public final void operateEvent(T value, Event event) {
        final int type = event.getOperationType();
        if (JsonOperationType.isCustomOperation(type)) {
            operateCustomEvent(value, event);
        } else {
            if (type == 0) {
                // TODo 调用其他已经实现好的函数
            }
        }
    }


    // TODO 加上其他的已经实现好的函数

    // 定义一个自定义的，用户可以自己实现
    protected void operateCustomEvent(T value, Event event) {

    }
}
