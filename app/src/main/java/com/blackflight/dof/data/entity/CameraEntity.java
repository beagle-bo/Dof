package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */
public class CameraEntity implements DataEntity<UUID> {
    private final UUID mId;
    private final UUID mBodyId;
    private final UUID mLensId;

    public CameraEntity(UUID id, UUID bodyId, UUID lensId) {
        mId = id;
        mBodyId = bodyId;
        mLensId = lensId;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public UUID getBodyId() {
        return mBodyId;
    }

    public UUID getLensId() {
        return mLensId;
    }
}