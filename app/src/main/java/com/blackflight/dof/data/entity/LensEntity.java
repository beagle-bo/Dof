package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 11-11-16.
 */

public class LensEntity implements DataEntity<UUID> {
    private final UUID mId;
    private final UUID mLensTypeId;
    private final double mFocalDistance;
    private final double mApertureValue;

    private final double mSubjectDistance;

    public LensEntity(UUID id, UUID lensTypeId, double focalDistance,
                        double apertureValue, double subjectDistance) {
        mId = id;
        mLensTypeId = lensTypeId;
        mFocalDistance = focalDistance;
        mApertureValue = apertureValue;
        mSubjectDistance = subjectDistance;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public double getFocalDistance() {
        return mFocalDistance;
    }

    public UUID getLensTypeId() {
        return mLensTypeId;
    }

    public double getApertureValue() {
        return mApertureValue;
    }

    public double getSubjectDistance() {
        return mSubjectDistance;
    }

}
