package com.example.gsontest.eventtrack.internal.event;

public class JsonOperationType {

    public static final int TYPE_INCREASE = 0;

    public static final int TYPE_CUSTOM_START = 100;


    public static boolean isCustomOperation(int type) {
        return type >= TYPE_CUSTOM_START;
    }
}
