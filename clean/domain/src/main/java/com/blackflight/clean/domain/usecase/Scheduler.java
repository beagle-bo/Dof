package com.blackflight.clean.domain.usecase;

/**
 * Created by peter on 8-8-16.
 */
public interface Scheduler {
    void execute(Runnable runnable);
}
