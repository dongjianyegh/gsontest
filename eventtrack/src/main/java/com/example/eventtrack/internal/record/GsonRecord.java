package com.example.eventtrack.internal.record;

import java.lang.reflect.Type;

public class GsonRecord {
    private final Type mType;
    private final Class mClazz;
    private final GsonOperation mGsonOperation;

    @SuppressWarnings("unchecked")
    protected GsonRecord(final Type type, final Class clazz, final GsonOperation operation) {
        mType = type;
        mClazz = clazz;
        mGsonOperation = operation;
    }

    public Class getClazz() {
        return mClazz;
    }

    public GsonOperation getOperation() {
        return mGsonOperation;
    }

    public Type getType() {
        return mType;
    }

}
