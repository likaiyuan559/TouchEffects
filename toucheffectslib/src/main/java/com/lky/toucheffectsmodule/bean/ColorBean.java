package com.lky.toucheffectsmodule.bean;

public class ColorBean {

    private int pressedColor;
    private int normalColor;
    private RadiusBean mRadiusBean;

    public ColorBean(int normalColor,int pressedColor) {
        this.pressedColor = pressedColor;
        this.normalColor = normalColor;
    }

    public ColorBean(int normalColor,int pressedColor, RadiusBean radiusBean) {
        this.pressedColor = pressedColor;
        this.normalColor = normalColor;
        mRadiusBean = radiusBean;
    }

    public void setPressedColor(int pressedColor) {
        this.pressedColor = pressedColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public void setRadiusBean(RadiusBean radiusBean) {
        mRadiusBean = radiusBean;
    }

    public int getPressedColor() {
        return pressedColor;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public RadiusBean getRadiusBean() {
        return mRadiusBean;
    }
}
