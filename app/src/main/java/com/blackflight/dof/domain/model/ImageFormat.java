package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class ImageFormat implements Entity<UUID> {
    private final UUID mId;
    private double mWidth;
    private double mHeight;

    public ImageFormat(double width, double height) {
        this(UUID.randomUUID(), width, height);
    }

    public ImageFormat(UUID id, double width, double height) {
        mId = id;
        mWidth = width;
        mHeight = height;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public double getWidth() {
        return mWidth;
    }

    public double getHeight() {
        return mHeight;
    }

    public double getDiameter() {
        return Math.sqrt(mWidth * mWidth + mHeight * mHeight);
    }


}
