package com.blackflight.clean.domain.usecase;

/**
 * Created by peter on 10-8-16.
 */
public interface UseCaseCallback<R extends UseCase.ResponseValue> {
    void onSuccess(R response);

    void onError();
}

