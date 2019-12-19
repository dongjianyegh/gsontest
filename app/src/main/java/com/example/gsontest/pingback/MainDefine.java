package com.example.gsontest.pingback;

import com.example.eventtrack.EventDefine;
import com.example.eventtrack.InstantGetter;
import com.example.eventtrack.internal.record.CreatorByOperation;
import com.example.eventtrack.internal.record.GsonRecord;
import com.example.gsontest.pingback.bean.DetailInfoOperation;
import com.example.gsontest.pingback.bean.GsonLanguageLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO 做成注解
public class MainDefine implements EventDefine {

    protected final static String EC_1 = "ec1";
    protected final static String EC_2 = "ec2";
    protected final static String EC_3 = "ec3";

    @Override
    public Map<String, GsonRecord> getJsonDefines() {
        final Map<String, GsonRecord> result = new HashMap<>();

        result.put(EC_1, new CreatorByOperation<GsonLanguageLayout>(){}.createGsonRecord());
        result.put(EC_2, new CreatorByOperation<GsonLanguageLayout>(){}.createGsonRecord());
        result.put(EC_3, new CreatorByOperation<DetailInfoOperation>(){}.createGsonRecord());

        return result;

    }

    @Override
    public Set<String> getNumberDefines() {
        return null;
    }

    @Override
    public InstantGetter getInstanceGetter() {
        return new ATestGetter();
    }
}
