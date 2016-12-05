package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class Body implements Entity<UUID> {
    private final UUID mId;
    private BodyType mBodyType;

    public Body(BodyType bodyType) {
        this(UUID.randomUUID(), bodyType);
    }

    public Body(UUID id, BodyType bodyType) {
        mId = id;
        mBodyType = bodyType;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public BodyType getBodyType() {
        return mBodyType;
    }

    public double getCircleOfConfusion() {
        return mBodyType.getCircleOfConfusion();
    }
}

