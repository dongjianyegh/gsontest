package com.example.eventtrack;


import com.example.eventtrack.internal.record.GsonRecord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RecordFactory {


    private final Map<String, GsonRecord> mJsonRecordDefines = new HashMap<>();
    private final Set<String> mJsonKeys = new HashSet<>();

    private final Set<String> mNumberKeys = new HashSet<>();

    final Set<InstantGetter> mInstantGetters = new HashSet<>();

    private final Set<EventDefine> mDefineRegisters = new HashSet<>();

    private volatile boolean mInited = false;

    void registRecordDefine(EventDefine eventDefine) {
        mDefineRegisters.add(eventDefine);
    }

    private void init() {
        if (!mInited) {
            synchronized (mJsonRecordDefines) {
                if (!mInited) {
                    addAllDefinesByAop();
                    defineAllRecordUnlocked();
                    mInited = true;
                }
            }
        }
    }

    private void defineAllRecordUnlocked() {
        for (EventDefine define : mDefineRegisters) {
            if (define == null) {
                continue;
            }
            defineJsonRecordUnlocked(define);
            defineNumberRecordUnlocked(define);

            final InstantGetter getter = define.getInstanceGetter();
            if (getter != null) {
                mInstantGetters.add(getter);
            }
        }
    }

    // TODO 注解要修改的函数
    private void addAllDefinesByAop() {
        // 注解生成如下代码
//        mDefineRegisters.add(new TestDefine());
    }

    private void defineNumberRecordUnlocked(EventDefine define) {
        if (define == null) {
            return;
        }

        final Set<String> keys = define.getNumberDefines();
        if (keys == null) {
            return;
        }

        for (String key : keys) {
            if (!mNumberKeys.add(key)) {
                throw new RuntimeException("Number key repeat define " + key);
            }
        }
    }

    private void defineJsonRecordUnlocked(EventDefine define) {
        if (define == null) {
            return;
        }

        final Map<String, GsonRecord> recordMap = define.getJsonDefines();
        if (recordMap == null) {
            return;
        }

        for (Map.Entry<String, GsonRecord> entry : recordMap.entrySet()) {
            if (mJsonRecordDefines.put(entry.getKey(), entry.getValue()) != null) {
                throw new RuntimeException("JsonRecord key repeat define " + entry.getKey());
            }
            mJsonKeys.add(entry.getKey());
        }
    }

    GsonRecord getJsonRecord(String key) {
        init();
        final GsonRecord record = mJsonRecordDefines.get(key);
        if (record == null) {
            throw new RuntimeException("Unsuportted record type");
        }

        return record;
    }

    boolean hasNumberRecord(String key) {
        init();
        if (!mNumberKeys.contains(key)) {
            throw new RuntimeException("unsupported number type");
        }

        return true;
    }

    Set<String> getJsonKeys() {
        init();
        return mJsonKeys;
    }

    Set<String> getNumberKeys() {
        init();
        return mNumberKeys;
    }

}
