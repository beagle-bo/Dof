package com.blackflight.clean.presentation;

import com.blackflight.clean.domain.usecase.UseCaseHandler;
import com.blackflight.clean.presentation.component.CleanAndroidComponent;
import com.blackflight.clean.presentation.component.DaggerCleanAndroidComponent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void getUseCaseHandler() throws Exception {
        CleanAndroidComponent component = DaggerCleanAndroidComponent.create();
        UseCaseHandler useCaseHandler = component.useCaseHandler();
        assertNotNull(useCaseHandler);
    }
}