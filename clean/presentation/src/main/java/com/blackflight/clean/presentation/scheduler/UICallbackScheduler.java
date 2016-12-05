package com.blackflight.clean.presentation.scheduler;

import android.os.Handler;
import com.blackflight.clean.domain.usecase.Scheduler;

/**
 * Created by peter on 9-8-16.
 */
public class UICallbackScheduler implements Scheduler {
    private final Handler mHandler = new Handler();

    @Override
    public void execute(Runnable runnable) {
        mHandler.post(runnable);
    }
}
