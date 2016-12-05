package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class BodyType implements Entity<UUID> {
    private final UUID mId;
    private String mModelName;
    private ImageFormat mImageFormat;

    public BodyType(String modelName, ImageFormat imageFormat) {
        this(UUID.randomUUID(), modelName, imageFormat);
    }

    public BodyType(UUID id, String modelName, ImageFormat imageFormat) {
        mId = id;
        mModelName = modelName;
        mImageFormat = imageFormat;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public String getModelName() {
        return mModelName;
    }

    public ImageFormat getImageFormat() {
        return mImageFormat;
    }

    public double getCircleOfConfusion() {
        return mImageFormat.getDiameter() / 1500;
    }


}
