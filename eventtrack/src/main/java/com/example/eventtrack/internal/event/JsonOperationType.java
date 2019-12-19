package com.example.eventtrack.internal.event;

public class JsonOperationType {

    public static final int TYPE_INCREASE = -100;

    public static final int TYPE_CUSTOM_START = 0;


    public static boolean isCustomOperation(int type) {
        return type >= TYPE_CUSTOM_START;
    }
}
