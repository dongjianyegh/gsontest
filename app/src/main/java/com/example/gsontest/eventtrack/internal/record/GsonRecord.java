package com.example.gsontest.eventtrack.internal.record;

// 两种方法，一种是每次都必须生命一个类从GsonRecord继承(用反射的方式来获取类型)，一种是构造的时候直接给个class，
// 第二种貌似更方便一点，效率更高，反正这些都是提前定义好的

public final class GsonRecord {

    private final Class mClazz;
    private final GsonOperation mGsonOperation;

    public GsonRecord(Class clazz, GsonOperation gsonOperation) {
        mClazz = clazz;
        mGsonOperation = gsonOperation;
    }

    public Class getClazz() {
        return mClazz;
    }

    public GsonOperation getOperation() {
        return mGsonOperation;
    }
}
