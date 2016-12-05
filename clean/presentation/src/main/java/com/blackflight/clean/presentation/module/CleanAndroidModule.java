package com.blackflight.clean.presentation.module;

import com.blackflight.clean.domain.usecase.Scheduler;
import com.blackflight.clean.presentation.scheduler.UICallbackScheduler;
import com.blackflight.clean.presentation.scheduler.UseCaseThreadPoolScheduler;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Created by peter on 11-8-16.
 */
@Module
public class CleanAndroidModule {

    @Provides @Named("UseCaseScheduler") @Singleton public Scheduler provideUseCaseScheduler() {
        return new UseCaseThreadPoolScheduler();
    }

    @Provides @Named("CallbackScheduler") @Singleton public Scheduler provideCallbackScheduler() {
        return new UICallbackScheduler();
    }
}
