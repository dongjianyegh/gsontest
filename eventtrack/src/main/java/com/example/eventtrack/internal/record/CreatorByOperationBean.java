package com.example.eventtrack.internal.record;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CreatorByOperationBean<OperationType, BeanType> extends GsonRecordCreator {

    @SuppressWarnings("unchecked")
    protected CreatorByOperationBean() {
        final Type type = getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            throw new RuntimeException("CreatorByOperationBean must be parameterized type");
        }

        final Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypes == null || actualTypes.length != 2) {
            throw new RuntimeException("GsonRecord must have 2 parameterized type");
        }

        mBeanCache = getTypeClassFromCache(actualTypes[1]);

        final Type operationType = actualTypes[0];
        if (!(operationType instanceof Class)) {
            throw new RuntimeException("GsonRecord second parameterized type must be class");
        }

        final Type operationGenericType = ((Class) operationType).getGenericSuperclass();
        if (operationGenericType instanceof ParameterizedType) {
            // operationType 如果是范型，必须和指定的范型相匹配，
        }

        mOperation = getGsonOperationFromCache((Class) operationType);
    }
}
