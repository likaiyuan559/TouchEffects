package com.lky.toucheffectsmodule;


import com.lky.toucheffectsmodule.bean.ColorBean;
import com.lky.toucheffectsmodule.bean.ScaleBean;
import com.lky.toucheffectsmodule.bean.extra.BaseExtraBean;
import com.lky.toucheffectsmodule.bean.extra.ExtraAspectRatioBean;
import com.lky.toucheffectsmodule.proxy.TouchEffectsCreateViewSubject;
import com.lky.toucheffectsmodule.proxy.TouchEffectsViewProxy;
import com.lky.toucheffectsmodule.types.TouchEffectsExtraType;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import com.lky.toucheffectsmodule.utils.TypeUtils;

import java.util.HashMap;

import androidx.annotation.NonNull;

public class TouchEffectsManager {

    public static final int NONE_TYPE = -1;
    public static final int SCALE_TYPE = 0;
    public static final int RIPPLE_TYPE = 1;
    public static final int STATE_TYPE = 2;
    public static final int SHAKE_TYPE = 3;
    public static final int RIPPLE_1_TYPE = 4;

    private static volatile TouchEffectsManager mInstance;
    private static TouchEffectsWholeType mTouchEffectsWholeType;
    private static HashMap<String,TouchEffectsWholeType> mViewTypes;
    private static TouchEffectsViewProxy mViewSubject;
    private static ColorBean mColorBean;
    private static ScaleBean mScaleBean;
    private static TouchEffectsWholeType mListWholeType;
    private static HashMap<TouchEffectsExtraType, BaseExtraBean> mExtraTypeMap = new HashMap<>();


    private TouchEffectsManager() {
        this.mViewTypes = new HashMap<>();
        mViewSubject = new TouchEffectsViewProxy(new TouchEffectsCreateViewSubject(mTouchEffectsWholeType,mColorBean));
    }

    private static TouchEffectsManager getInstance(){
        if(mInstance == null){
            synchronized (TouchEffectsManager.class){
                if(mInstance == null){
                    mInstance = new TouchEffectsManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 创建Manager，初始化
     * @param touchEffectsWholeType 全局使用什么模式{@link TouchEffectsWholeType}
     * @return
     */
    public static TouchEffectsManager build(@NonNull TouchEffectsWholeType touchEffectsWholeType){
        mTouchEffectsWholeType = touchEffectsWholeType;
        if(mColorBean == null){
            mColorBean = new ColorBean(0x3D000000,0x3D000000);
        }
        return getInstance();
    }

    /**
     * 创建Manager，初始化
     * @param touchEffectsWholeType 全局使用什么模式{@link TouchEffectsWholeType}
     * @return
     */
    public static TouchEffectsManager build(@NonNull TouchEffectsWholeType touchEffectsWholeType, int normalColor, int pressedColor){
        mTouchEffectsWholeType = touchEffectsWholeType;
        if(mColorBean == null){
            mColorBean = new ColorBean(normalColor,pressedColor);
        }else{
            mColorBean.setNormalColor(normalColor);
            mColorBean.setPressedColor(pressedColor);
        }
        return getInstance();
    }

    /**
     * 添加支持的视图的类型
     * @param viewTypes 支持的类型 {@link TouchEffectsViewType}
     * @return
     */
    public TouchEffectsManager addViewTypes(@TouchEffectsViewType String... viewTypes){
        addViewTypes(mTouchEffectsWholeType,viewTypes);
        return mInstance;
    }

    /**
     * 添加支持的视图的类型
     * @param wholeType 支持的类型中适用于什么模式（如TextView使用EFFECTS,Button使用RIPPLE）{@link TouchEffectsWholeType}
     * @param viewTypes 支持的类型 {@link TouchEffectsViewType}
     * @return
     */
    public TouchEffectsManager addViewTypes(TouchEffectsWholeType wholeType,@TouchEffectsViewType String... viewTypes){
        for(String viewType:viewTypes){
            addViewType(wholeType,viewType);
        }
        return mInstance;
    }

    /**
     * 添加支持的视图的类型
     * @param viewType 支持的类型 {@link TouchEffectsViewType}
     * @return
     */
    public TouchEffectsManager addViewType(@TouchEffectsViewType String viewType){
        addViewType(mTouchEffectsWholeType,viewType);
        return mInstance;
    }

    /**
     * 添加支持的视图的类型
     * @param wholeType 支持的类型中适用于什么模式（如TextView使用EFFECTS,Button使用RIPPLE）{@link TouchEffectsWholeType}
     * @param viewType 支持的类型 {@link TouchEffectsViewType}
     * @return
     */
    public TouchEffectsManager addViewType(TouchEffectsWholeType wholeType,@TouchEffectsViewType String viewType){
        if(viewType.equals(TouchEffectsViewType.ALL)){
            mViewTypes.clear();
            for(String viewType1:TypeUtils.getAllViewTypes()){
                mViewTypes.put(viewType1,wholeType);
            }
        }else{
            mViewTypes.put(viewType,wholeType);
        }
        return mInstance;
    }

    public TouchEffectsManager setListWholeType(TouchEffectsWholeType listWholeType) {
        mListWholeType = listWholeType;
        return mInstance;
    }

    /**
     * 设置View在某种模式中，宽高比达到什么程度时，使用另外的模式
     * @param width 宽度，width < 1f : 使用比例  width > 1 : 不使用比例，当大于这个值的时候触发，与height同时生效
     * @param height 宽度，height < 1f : 使用比例  height > 1 : 不使用比例，当大于这个值的时候触发，与width同时生效
     * @param wholeType 更换的模式
     *                  ！！！注意，优先级大于listType
     * @return
     */
    public TouchEffectsManager setAspectRatioType(float width,float height,TouchEffectsWholeType wholeType){
        ExtraAspectRatioBean extraAspectRatioBean = new ExtraAspectRatioBean(wholeType,width,height);
        mExtraTypeMap.put(TouchEffectsExtraType.AspectRatio,extraAspectRatioBean);
        return mInstance;
    }

    /**
     * 设置View在某种模式中，宽高比达到什么程度时，使用另外的模式
     * @param aspectRatio 宽高比，0 < aspectRatio < 10,如果小于1，则以height为基准，大于等于1，则以width为基准
     * @param wholeType 更换的模式
     *                  ！！！注意，优先级大于listType
     * @return
     */
    public TouchEffectsManager setAspectRatioType(float aspectRatio,TouchEffectsWholeType wholeType){
        if(aspectRatio > 10 || aspectRatio <= 0){
            throw new IllegalArgumentException("宽高比不能大于10或小于等于0");
        }
        float height = 1f;
        float width = aspectRatio;
        if(aspectRatio > 1f){
            width = aspectRatio / 10f;
            height = 1f / 10f;
        }
        ExtraAspectRatioBean extraAspectRatioBean = new ExtraAspectRatioBean(wholeType,width,height);
        mExtraTypeMap.put(TouchEffectsExtraType.AspectRatio,extraAspectRatioBean);
        return mInstance;
    }

    public static HashMap<TouchEffectsExtraType, BaseExtraBean> getExtraTypeMap() {
        return mExtraTypeMap;
    }

    public static TouchEffectsWholeType getListWholeType() {
        return mListWholeType;
    }

    /**
     * 获取所有设置的类型
     * @return
     */
    public static HashMap<String, TouchEffectsWholeType> getViewTypes() {
        if(mViewTypes == null){
            throw new RuntimeException("please initialize in Application");
        }
        return mViewTypes;
    }

    public static TouchEffectsViewProxy getViewSubject() {
        return mViewSubject;
    }

    public static ColorBean getColorBean() {
        return mColorBean;
    }

    public static ScaleBean getScaleBean() {
        return mScaleBean;
    }
}
