package com.blackflight.dof.data.entity;

import com.blackflight.clean.data.entity.DataEntity;

import java.util.UUID;

/**
 * Created by peter on 19-9-16.
 */
public class LensTypeEntity implements DataEntity<UUID> {

    private final UUID mId;
    private final String mModelName;
    private final double mMinFocalDistance;
    private final double mMaxFocalDistance;
    private final String mFStopScale;
    private final double mMinApertureValue;
    private final double mMaxApertureValue;

    public LensTypeEntity(UUID id, String modelName, double minFocalDistance, double maxFocalDistance,
                          String FStopScale, double minApertureValue, double maxApertureValue) {
        mId = id;
        mModelName = modelName;
        mMinFocalDistance = minFocalDistance;
        mMaxFocalDistance = maxFocalDistance;
        mFStopScale = FStopScale;
        mMinApertureValue = minApertureValue;
        mMaxApertureValue = maxApertureValue;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public String getModelName() {
        return mModelName;
    }

    public double getMinFocalDistance() {
        return mMinFocalDistance;
    }

    public double getMaxFocalDistance() {
        return mMaxFocalDistance;
    }

    public String getFStopScale() {
        return mFStopScale;
    }

    public double getMinApertureValue() {
        return mMinApertureValue;
    }

    public double getMaxApertureValue() {
        return mMaxApertureValue;
    }
}
