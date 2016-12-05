package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class LensType implements Entity<UUID> {

    public enum FStopScale { FULL_STOP, HALF_STOP, ONE_THIRD_STOP}

    private final UUID mId;
    private String mModelName;
    private double mMinFocalDistance;
    private double mMaxFocalDistance;

    private FStopScale mFStopScale;
    private double mMinApertureValue;
    private double mMaxApertureValue;

    public LensType(String modelName, double minFocalDistance, double maxFocalDistance,
             FStopScale fStopScale, double minApertureValue, double maxApertureValue){
        this(UUID.randomUUID(), modelName, minFocalDistance, maxFocalDistance,
                fStopScale, minApertureValue, maxApertureValue);
    }

    public LensType(UUID id, String modelName, double minFocalDistance, double maxFocalDistance,
             FStopScale fStopScale, double minApertureValue, double maxApertureValue){
        mId = id;
        mModelName = modelName;
        mMinFocalDistance = minFocalDistance;
        mMaxFocalDistance = maxFocalDistance;
        mFStopScale = fStopScale;
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

    public boolean isZoom() {
        return mMinFocalDistance < mMaxFocalDistance;
    }

    public double getMinFocalDistance() {
        return mMinFocalDistance;
    }

    public double getMaxFocalDistance() {
        return mMaxFocalDistance;
    }

    public FStopScale getFStopScale() {
        return mFStopScale;
    }

    public double getMinApertureValue() {
        return mMinApertureValue;
    }

    public double getMaxApertureValue() {
        return mMaxApertureValue;
    }
}
