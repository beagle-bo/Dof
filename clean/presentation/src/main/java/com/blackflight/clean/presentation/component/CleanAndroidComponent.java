package com.blackflight.clean.presentation.component;

import com.blackflight.clean.domain.usecase.UseCaseHandler;
import com.blackflight.clean.presentation.module.CleanAndroidModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by peter on 9-9-16.
 */
@Component(modules = CleanAndroidModule.class)
@Singleton
public interface CleanAndroidComponent {
    UseCaseHandler useCaseHandler();
}
