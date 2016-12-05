package com.blackflight.clean.domain.usecase;

/**
 * Created by peter on 8-8-16.
 */
public interface UseCase<A extends UseCase.RequestValues, R extends UseCase.ResponseValue> {

    R execute(A args) throws Exception;

    interface RequestValues {
    }

    interface ResponseValue {
    }
}
