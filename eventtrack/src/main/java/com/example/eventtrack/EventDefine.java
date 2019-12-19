package com.example.eventtrack;


import com.example.eventtrack.internal.record.GsonRecord;

import java.util.Map;
import java.util.Set;

public interface EventDefine {

    public Map<String, GsonRecord> getJsonDefines();
    public Set<String> getNumberDefines();
}
