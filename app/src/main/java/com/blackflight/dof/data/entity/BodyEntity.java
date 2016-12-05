package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 11-11-16.
 */

public class BodyEntity implements DataEntity<UUID> {
    private final UUID mId;
    private final UUID mBodyTypeId;

    public BodyEntity(UUID id, UUID bodyTypeId) {
        mId = id;
        mBodyTypeId = bodyTypeId;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public UUID getBodyTypeId() {
        return mBodyTypeId;
    }
}
