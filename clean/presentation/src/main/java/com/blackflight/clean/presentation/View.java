package com.blackflight.clean.presentation;

/**
 * Created by peter on 13-9-16.
 */
public interface View<T extends Presenter> {

    void setPresenter(T presenter);
}