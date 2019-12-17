package com.example.gsontest.eventtrack.internal.record;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import androidx.annotation.NonNull;

public abstract class GsonRecordCreator {

    protected Pair<Type, Class> mBeanCache;
    protected GsonOperation mOperation;

    // 针对Type，Type对应的是BeanType,
    private final static HashMap<Type, Pair<Type, Class>> TYPE_CLASS_HASH_MAP = new HashMap<>();
    // 针对GsonOperation，还有个缓存
    private final static HashMap<Class, GsonOperation> GSON_OPERATION_CACHE = new HashMap<>();
    // 针对整个GsonRecord，还有一个缓存
    private final static HashMap<Type, HashMap<Class, GsonRecord>> GSON_RECORD_CACHE = new HashMap<>();

    static Pair<Type, Class> getTypeClassFromCache(Type type) {
        Pair<Type, Class> beanTypes = TYPE_CLASS_HASH_MAP.get(type);
        if (beanTypes == null) {
            final TypeToken typeToken = TypeToken.get(type);
            beanTypes = new Pair<>(typeToken.getType(), typeToken.getRawType());
            TYPE_CLASS_HASH_MAP.put(type, beanTypes);
        }

        return beanTypes;
    }

    static GsonOperation getGsonOperationFromCache(Class clazz) {
        GsonOperation operation = GSON_OPERATION_CACHE.get(clazz);
        if (operation == null) {
            try {
                operation = (GsonOperation) clazz.newInstance();
                GSON_OPERATION_CACHE.put(clazz, operation);
            } catch (Throwable ignored) {
                throw new RuntimeException("GsonOperation new instance failed");
            }
        }

        return operation;
    }

    static GsonRecord getGsonRecordFromCache(Type type, Class clazz) {
        HashMap<Class, GsonRecord> gsonRecordHashMap = GSON_RECORD_CACHE.get(type);
        if (gsonRecordHashMap == null) {
            return null;
        } else {
            return gsonRecordHashMap.get(clazz);
        }
    }

    static void setGsonRecordToCache(Type type, Class clazz, GsonRecord record) {
        HashMap<Class, GsonRecord> gsonRecordHashMap = GSON_RECORD_CACHE.get(type);
        if (gsonRecordHashMap == null) {
            gsonRecordHashMap = new HashMap<>();
            GSON_RECORD_CACHE.put(type, gsonRecordHashMap);
        }

        gsonRecordHashMap.put(clazz, record);
    }

    public final @NonNull GsonRecord createGsonRecord() {
        if (mBeanCache == null || mOperation == null) {
            return null;
        }

        GsonRecord record = getGsonRecordFromCache(mBeanCache.first, mOperation.getClass());
        if (record == null) {
            record = new GsonRecord(mBeanCache.first, mOperation.getClass(), mOperation);
            setGsonRecordToCache(mBeanCache.first, mOperation.getClass(), record);
        }

        return record;
    }
}
