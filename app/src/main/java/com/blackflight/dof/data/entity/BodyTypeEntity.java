package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */
public class BodyTypeEntity implements DataEntity<UUID> {
    private final UUID mId;
    private final String mModelName;
    private final UUID mImageFormatId;

    public BodyTypeEntity(UUID id, String modelName, UUID imageFormatId) {
        mId = id;
        mModelName = modelName;
        mImageFormatId = imageFormatId;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public UUID getImageFormatId() {
        return mImageFormatId;
    }

    public String getModelName() {
        return mModelName;
    }
}
