package com.lky.toucheffectsmodule.bean;

public class ScaleBean {

    private int mAnimationDuration;
    private int mShakeScale;

    public ScaleBean(int animationDuration, int shakeScale) {
        mAnimationDuration = animationDuration;
        mShakeScale = shakeScale;
    }

    public int getAnimationDuration() {
        return mAnimationDuration;
    }

    public int getShakeScale() {
        return mShakeScale;
    }
}
