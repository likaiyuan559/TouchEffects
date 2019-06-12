package com.lky.toucheffectsviewdemo.bean;

public class JumpBean {

    private String mTitleName;
    private Class mJumpClass;

    public JumpBean(String titleName, Class jumpClass) {
        mTitleName = titleName;
        mJumpClass = jumpClass;
    }

    public String getTitleName() {
        return mTitleName;
    }

    public Class getJumpClass() {
        return mJumpClass;
    }
}
