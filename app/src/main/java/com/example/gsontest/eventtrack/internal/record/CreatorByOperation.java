package com.example.gsontest.eventtrack.internal.record;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CreatorByOperation<OperationType> extends GsonRecordCreator {

    @SuppressWarnings("unchecked")
    protected CreatorByOperation() {
        final Type type = getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            throw new RuntimeException("CreatorByOperation must be parameterized type");
        }

        final Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypes == null || actualTypes.length != 1) {
            throw new RuntimeException("CreatorByOperation must have1 parameterized type");
        }

        final Type operationType = actualTypes[0];
        if (!(operationType instanceof Class)) {
            throw new RuntimeException("GsonRecord second parameterized type must be class");
        }

        final Type operationGenericType = ((Class) operationType).getGenericSuperclass();
        if (!(operationGenericType instanceof ParameterizedType)) {
            throw new RuntimeException("CreatorByOperation must be parameterized type");
        }

        final Type[] beanTypes = ((ParameterizedType) operationGenericType).getActualTypeArguments();
        if (beanTypes == null || beanTypes.length != 1) {
            throw new RuntimeException("CreatorByOperation must be parameterized type");
        }

        mBeanCache = getTypeClassFromCache(beanTypes[0]);
        mOperation = getGsonOperationFromCache((Class) operationType);
    }
}
