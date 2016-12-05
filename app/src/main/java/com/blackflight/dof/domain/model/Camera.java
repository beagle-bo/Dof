package com.blackflight.dof.domain.model;

import com.blackflight.clean.domain.model.Entity;

import java.util.UUID;

/**
 * Created by peter on 13-9-16.
 */
public class Camera implements Entity<UUID> {
    private final UUID mId;
    private Body mBody;
    private Lens mLens;

    public Camera(Body body) {
        this(UUID.randomUUID(), body, null);
    }

    public Camera(UUID id, Body body, Lens lens) {
        mId = id;
        mBody = body;
        mLens = lens;
    }

    @Override
    public UUID getId() { return mId; }

    public Body getBody() {
        return mBody;
    }

    public Lens getLens() {
        return mLens;
    }

    public boolean hasMountedLens() {
        return mLens != null;
    }

    public void mountLens(Lens lens) {
        mLens = lens;
    }

    public double getHyperfocalDistance() {
        double f = mLens.getFocalDistance();
        double N = mLens.getFNumber();
        double c = mBody.getCircleOfConfusion();
        double H = f + (f*f)/(N*c);
        return H;
    }

    public double getNearLimit() {
        double H = getHyperfocalDistance();
        double s = mLens.getSubjectDistance();
        double Dn = (H*s)/(H+s);
        return Dn;
    }

    public double getFarLimit() {
        double H = getHyperfocalDistance();
        double s = mLens.getSubjectDistance();
        double Df;
        if (s < H)
            Df = (H*s)/(H-s);
        else
            Df = Double.POSITIVE_INFINITY;
        return Df;
    }

    public double getDepthOfField() {
        double H = getHyperfocalDistance();
        double s = mLens.getSubjectDistance();
        double DoF;
        if (s < H)
            DoF = (2*H*s*s)/(H*H-s*s);
        else
            DoF = Double.POSITIVE_INFINITY;
        return DoF;
    }
}
