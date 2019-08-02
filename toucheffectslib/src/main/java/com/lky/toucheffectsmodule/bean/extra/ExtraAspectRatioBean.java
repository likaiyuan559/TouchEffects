package com.lky.toucheffectsmodule.bean.extra;

import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class ExtraAspectRatioBean extends BaseExtraBean {

    private float mWidth;
    private float mHeight;

    public ExtraAspectRatioBean(TouchEffectsWholeType wholeType,float width,float height) {
        mWholeType = wholeType;
        mWidth = width;
        mHeight = height;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }
}
