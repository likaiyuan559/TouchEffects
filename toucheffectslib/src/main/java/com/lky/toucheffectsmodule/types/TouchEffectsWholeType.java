package com.lky.toucheffectsmodule.types;

public enum  TouchEffectsWholeType implements TouchEffectsType{
    NONE,           //不自动为控件添加效果
    SCALE,          //为系统控件添加缩放效果
    RIPPLE,         //为系统控件添加点击水波纹效果
    STATE,          //为系统控件添加普通点击响应效果
    RIPPLE_1,       //为系统控件添加点击水波纹1效果
//    ALL_SCALE,      //为所有控件（包括自定义控件）添加缩放效果
//    ALL_RIPPLE,     //为所有控件（包括自定义控件）添加点击水波纹效果
//    ALL_STATE,      //为所有控件（包括自定义控件）添加普通点击响应效果
}
