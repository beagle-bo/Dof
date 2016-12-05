package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class Lens implements Entity<UUID> {

    private final UUID mId;
    private LensType mLensType;
    private double mFocalDistance;
    private double mApertureValue;
    private double mSubjectDistance;

    public Lens(LensType lensType) {
        this(UUID.randomUUID(), lensType, lensType.getMinFocalDistance(), lensType.getMinApertureValue(), 1000.0);
    }

    public Lens(UUID id, LensType lensType, double focalDistance, double apertureValue, double subjectDistance) {
        mId = id;
        mLensType = lensType;
        mFocalDistance = focalDistance;
        mApertureValue = apertureValue;
        mSubjectDistance = subjectDistance;
    }

    @Override
    public UUID getId() {
        return mId;
    }

    public LensType getLensType() {
        return mLensType;
    }

    public double getFocalDistance() {
        return mFocalDistance;
    }

    public void setFocalDistance(double focalDistance) {
        mFocalDistance = focalDistance;
    }

    public double getApertureValue() {
        return mApertureValue;
    }

    public void setApertureValue(double apertureValue) {
        mApertureValue = apertureValue;
    }

    public double getFNumber() {
        return Math.pow(2.0, 0.5 * mApertureValue);
    }

    public double getSubjectDistance() {
        return mSubjectDistance;
    }

    public void setSubjectDistance(double subjectDistance) {
        mSubjectDistance = subjectDistance;
    }
}
