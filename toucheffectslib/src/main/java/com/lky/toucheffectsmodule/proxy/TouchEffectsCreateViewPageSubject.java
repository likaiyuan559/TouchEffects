package com.lky.toucheffectsmodule.proxy;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.lky.toucheffectsmodule.R;
import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.bean.ColorBean;
import com.lky.toucheffectsmodule.bean.ScaleBean;
import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchRipple1Adapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchRippleAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchScaleAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchShakeAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchStateAdapter;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsButton;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsConstraintLayout;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsFrameLayout;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsImageButton;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsImageView;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsLinearLayout;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsRelativeLayout;
import com.lky.toucheffectsmodule.effects_view.TouchEffectsTextView;
import com.lky.toucheffectsmodule.impl.TouchEffectsViewSubject;
import com.lky.toucheffectsmodule.types.TouchEffectsSingleType;
import com.lky.toucheffectsmodule.types.TouchEffectsType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

/**
 * 页面中单独设置Type的Subject，全局设置及列表设置无效
 */
public class TouchEffectsCreateViewPageSubject implements TouchEffectsViewSubject {


    private TouchEffectsWholeType mTouchEffectsWholeType;
    private ScaleBean mScaleBean;
    private ColorBean mColorBean;
    private final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    public TouchEffectsCreateViewPageSubject(TouchEffectsWholeType touchEffectsWholeType) {
        mTouchEffectsWholeType = touchEffectsWholeType;
    }

    public TouchEffectsCreateViewPageSubject(TouchEffectsWholeType touchEffectsWholeType, ScaleBean scaleBean) {
        mTouchEffectsWholeType = touchEffectsWholeType;
        mScaleBean = scaleBean;
    }

    public TouchEffectsCreateViewPageSubject(TouchEffectsWholeType touchEffectsWholeType, ColorBean colorBean) {
        mTouchEffectsWholeType = touchEffectsWholeType;
        mColorBean = colorBean;
    }

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.contains(".") && !name.startsWith("android")) {//不处理自定义控件
            return view;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
        int type = ta.getInt(R.styleable.TouchEffectsView_touch_effects_type,TouchEffectsManager.NONE_TYPE);
        boolean isForbid = ta.getBoolean(R.styleable.TouchEffectsView_touch_effects_forbid,false);
        if(isForbid) {//如果设置了禁用,则优先级最高，不处理
            return view;
        }
        if (type != TouchEffectsManager.NONE_TYPE) {//只要在View中设置了type，除非是none，否则不论全局何种模式，都优先根据type
            if (type == TouchEffectsManager.RIPPLE_TYPE) {
                view = parseTypeView(TouchEffectsWholeType.RIPPLE,parent,name, context, attrs);
            } else if (type == TouchEffectsManager.RIPPLE_1_TYPE) {
                view = parseTypeView(TouchEffectsWholeType.RIPPLE_1,parent,name, context, attrs);
            }else if (type == TouchEffectsManager.STATE_TYPE) {
                view = parseTypeView(TouchEffectsWholeType.STATE,parent,name, context, attrs);
            } else if (type == TouchEffectsManager.SHAKE_TYPE) {
                view = parseTypeView(TouchEffectsSingleType.SHAKE,parent,name, context, attrs);
            } else {
                view = parseTypeView(TouchEffectsWholeType.SCALE,parent,name, context, attrs);
            }
        }else{
            view = parseTypeView(mTouchEffectsWholeType, parent,name,context,attrs);
        }
        return view;
    }

    private View parseTypeView(TouchEffectsType type, View parent, String name, Context context, AttributeSet attrs){
        View view = parseEffectsView(parent,type,name,context,attrs);
        return view;
    }

    /**
     * 解析缩放模式下的View
     * @param name
     * @param context
     * @param attrs
     * @return
     */
    private View parseEffectsView(View parent, TouchEffectsType wholeType, String name, Context context, AttributeSet attrs){
        return findSystemViewChange(name,wholeType,context,attrs);
    }

    /**
     * 如果是支持的View，则转换成TouchShake系列的View
     * @param name
     * @param context
     * @param attrs
     * @return
     */
    private View findSystemViewChange(String name, TouchEffectsType wholeType, Context context, AttributeSet attrs){
        View view = null;
        EffectsAdapter adapter = null;
        if(wholeType != null){
            adapter = getAdapter(wholeType);
        }
        if(adapter == null){
            return null;
        }
        view = checkViewName(name,context,attrs,adapter);
        return view;
    }

    private View checkViewName(String name,Context context, AttributeSet attrs,EffectsAdapter adapter){
        if(name.contains("AppCompat")){
            return checkExtendView(name,context,attrs,adapter);
        }else{
            return checkView(name,context,attrs,adapter);
        }
    }

    private View checkView(String name,Context context, AttributeSet attrs,EffectsAdapter adapter){
        View view = null;
        switch (name) {
            case "TextView":
                view = new TouchEffectsTextView(context, attrs,adapter);
                break;
            case "Button":
                view = new TouchEffectsButton(context, attrs,adapter);
                break;
            case "ImageView":
                view = new TouchEffectsImageView(context, attrs,adapter);
                break;
            case "ImageButton":
                view = new TouchEffectsImageButton(context, attrs,adapter);
                break;
            case "FrameLayout":
                view = new TouchEffectsFrameLayout(context, attrs,adapter);
                break;
            case "LinearLayout":
                view = new TouchEffectsLinearLayout(context, attrs,adapter);
                break;
            case "RelativeLayout":
                view = new TouchEffectsRelativeLayout(context, attrs,adapter);
                break;
            case "android.support.constraint.ConstraintLayout":
                view = new TouchEffectsConstraintLayout(context,attrs,adapter);
                break;
        }
        return view;
    }

    private View checkExtendView(String name,Context context, AttributeSet attrs,EffectsAdapter adapter){
        if(name.contains("TextView")){
            return new TouchEffectsTextView(context, attrs,adapter);
        }
        if(name.contains("Button")){
            return new TouchEffectsButton(context, attrs,adapter);
        }
        if(name.contains("ImageView")){
            return new TouchEffectsImageView(context, attrs,adapter);
        }
        if(name.contains("ImageButton")){
            return new TouchEffectsImageButton(context, attrs,adapter);
        }
        if(name.contains("FrameLayout")){
            return new TouchEffectsFrameLayout(context, attrs,adapter);
        }
        if(name.contains("LinearLayout")){
            return new TouchEffectsLinearLayout(context, attrs,adapter);
        }
        if(name.contains("RelativeLayout")){
            return new TouchEffectsRelativeLayout(context, attrs,adapter);
        }
        if(name.contains("ConstraintLayout")){
            return new TouchEffectsConstraintLayout(context,attrs,adapter);
        }
        return null;
    }

    private EffectsAdapter getAdapter(TouchEffectsType type){
        if(type instanceof TouchEffectsSingleType){
            TouchEffectsSingleType singleType = (TouchEffectsSingleType) type;
            switch (singleType){
                case SHAKE:
                    return new TouchShakeAdapter();
            }
        }else {
            TouchEffectsWholeType wholeType = (TouchEffectsWholeType) type;
            switch (wholeType){
                case SCALE:
                    return new TouchScaleAdapter(mScaleBean);
                case RIPPLE:
                    return new TouchRippleAdapter(TouchEffectsManager.getColorBean());
                case STATE:
                    return new TouchStateAdapter(TouchEffectsManager.getColorBean());
                case RIPPLE_1:
                    return new TouchRipple1Adapter(TouchEffectsManager.getColorBean());
            }
        }

        return null;
    }
}
