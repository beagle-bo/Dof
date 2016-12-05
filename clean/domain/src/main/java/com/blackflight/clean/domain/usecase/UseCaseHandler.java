package com.blackflight.clean.domain.usecase;

import javax.inject.*;

/**
 * Created by peter on 8-8-16.
 */
public class UseCaseHandler {

    private final Scheduler mUseCaseScheduler;
    private final Scheduler mCallbackScheduler;

    @Inject public UseCaseHandler(@Named("UseCaseScheduler") Scheduler useCaseScheduler,
                                  @Named("CallbackScheduler") Scheduler callbackScheduler) {
        mUseCaseScheduler = useCaseScheduler;
        mCallbackScheduler = callbackScheduler;
    }

    public <A extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            final UseCase<A, R> useCase, A values, UseCaseCallback<R> callback) {

        UseCaseCallbackWrapper<R> callbackWrapper = new UseCaseCallbackWrapper<>(callback, mCallbackScheduler);
        UseCaseWrapper<A, R> useCaseWrapper = new UseCaseWrapper<>(useCase, values, callbackWrapper, mUseCaseScheduler);

        useCaseWrapper.executeAsync();
    }

    private static final class UseCaseWrapper<A extends UseCase.RequestValues, R extends UseCase.ResponseValue> {

        UseCase<A, R> mUseCase;
        A mRequestValues;
        UseCaseCallback<R> mCallback;
        Scheduler mUseCaseScheduler;

        UseCaseWrapper(UseCase<A, R> useCase, A args, UseCaseCallback<R> callback,
                       Scheduler useCaseScheduler) {
            mUseCase = useCase;
            mRequestValues = args;
            mCallback = callback;
            mUseCaseScheduler = useCaseScheduler;
        }

        public void executeAsync() {
            mUseCaseScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    execute();
                }
            });
        }

        private void execute() {
            try {
                R response = mUseCase.execute(mRequestValues);
                mCallback.onSuccess(response);
            } catch (Exception e) {
                mCallback.onError();
            }
        }
    }

    private static final class UseCaseCallbackWrapper<R extends UseCase.ResponseValue> implements
            UseCaseCallback<R> {

        private final UseCaseCallback<R> mCallback;
        private final Scheduler mCallbackScheduler;

        public UseCaseCallbackWrapper(UseCaseCallback<R> callback, Scheduler callbackScheduler) {
            mCallback = callback;
            mCallbackScheduler = callbackScheduler;
        }

        @Override
        public void onSuccess(R response) {
            notifySuccessAsync(response);
        }

        @Override
        public void onError() {
            notifyErrorAsync();
        }

        public void notifySuccessAsync(final R response) {
            mCallbackScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    mCallback.onSuccess(response);
                }
            });
        }

        public void notifyErrorAsync() {
            mCallbackScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError();
                }
            });
        }
    }
}
