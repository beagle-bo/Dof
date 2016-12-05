package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */
public class ImageFormatEntity implements DataEntity<UUID> {

    private final UUID mId;
    private final double mWidth;
    private final double mHeight;

    public ImageFormatEntity(UUID id, double height, double width) {
        mId = id;
        mHeight = height;
        mWidth = width;
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
}
