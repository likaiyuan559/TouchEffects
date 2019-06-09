package com.lky.toucheffectsmodule.bean;

public class RadiusBean {
    private int mTopLeftRadius;
    private int mTopRightRadius;
    private int mBottomLeftRadius;
    private int mBottomRightRadius;

    public RadiusBean(int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        mTopLeftRadius = topLeftRadius;
        mTopRightRadius = topRightRadius;
        mBottomLeftRadius = bottomLeftRadius;
        mBottomRightRadius = bottomRightRadius;
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mTopLeftRadius = topLeftRadius;
    }

    public void setTopRightRadius(int topRightRadius) {
        mTopRightRadius = topRightRadius;
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mBottomLeftRadius = bottomLeftRadius;
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mBottomRightRadius = bottomRightRadius;
    }

    public int getTopLeftRadius() {
        return mTopLeftRadius;
    }

    public int getTopRightRadius() {
        return mTopRightRadius;
    }

    public int getBottomLeftRadius() {
        return mBottomLeftRadius;
    }

    public int getBottomRightRadius() {
        return mBottomRightRadius;
    }
}
