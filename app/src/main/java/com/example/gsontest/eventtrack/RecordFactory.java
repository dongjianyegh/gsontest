package com.example.gsontest.eventtrack;

import com.example.gsontest.eventtrack.bean.DetailInfo;
import com.example.gsontest.eventtrack.bean.DetailInfoOperation;
import com.example.gsontest.eventtrack.bean.GsonLanguageLayout;
import com.example.gsontest.eventtrack.internal.record.CreatorByOperation;
import com.example.gsontest.eventtrack.internal.record.CreatorByOperationBean;
import com.example.gsontest.eventtrack.internal.record.GsonOperation;
import com.example.gsontest.eventtrack.internal.record.GsonRecord;

import java.util.HashMap;
import java.util.Map;

public class RecordFactory {

    private final static Map<String, GsonRecord> JSON_RECORDS = new HashMap<>();



    public final static String SETUP_PAGE_SHOW = "b235";
    public final static String DEATIL_INFO_LIST = "c1";
    public final static String DETAIL_INFO = "c2";

    private final static GsonOperation DEFAULT_GSON_OPERATION = new GsonOperation();

    static {
        // TODO 把这个new GsonRecord做成注解形式
        JSON_RECORDS.put(SETUP_PAGE_SHOW, new CreatorByOperation<GsonLanguageLayout>(){}.createGsonRecord());
        JSON_RECORDS.put(DEATIL_INFO_LIST, new CreatorByOperation<DetailInfoOperation>(){}.createGsonRecord());
        JSON_RECORDS.put(DETAIL_INFO, new CreatorByOperationBean<GsonOperation, DetailInfo>(){}.createGsonRecord());

    }

    public static GsonRecord getRecord(String type) {
        final GsonRecord record = JSON_RECORDS.get(type);
        if (record == null) {
            throw new RuntimeException("Unsuportted record type");
        }

        return record;
    }
}
