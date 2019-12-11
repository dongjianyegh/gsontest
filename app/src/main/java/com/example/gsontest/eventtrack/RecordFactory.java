package com.example.gsontest.eventtrack;

import com.example.gsontest.eventtrack.bean.GsonLanguageLayout;
import com.example.gsontest.eventtrack.internal.record.GsonRecord;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class RecordFactory {

    private final static Map<String, GsonRecord> JSON_RECORDS = new HashMap<>();

    public final static String SETUP_PAGE_SHOW = "b235";

    static {


        GsonRecord languageLayout = new GsonRecord(
                (Class) TypeToken.get(HashMap.class).getType(),
                new GsonLanguageLayout());

        JSON_RECORDS.put(SETUP_PAGE_SHOW, languageLayout);
    }

    public static GsonRecord getRecord(String type) {
        final GsonRecord record = JSON_RECORDS.get(type);
        if (record == null) {
            throw new RuntimeException("Unsuportted record type");
        }

        return record;
    }
}
