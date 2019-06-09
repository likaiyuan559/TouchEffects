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

public class TouchEffectsCreateViewSubject implements TouchEffectsViewSubject {


    private TouchEffectsWholeType mTouchEffectsWholeType;
    private ScaleBean mScaleBean;
    private ColorBean mColorBean;
    private final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    public TouchEffectsCreateViewSubject(TouchEffectsWholeType touchEffectsWholeType) {
        mTouchEffectsWholeType = touchEffectsWholeType;
    }

    public TouchEffectsCreateViewSubject(TouchEffectsWholeType touchEffectsWholeType, ScaleBean scaleBean) {
        mTouchEffectsWholeType = touchEffectsWholeType;
        mScaleBean = scaleBean;
    }

    public TouchEffectsCreateViewSubject(TouchEffectsWholeType touchEffectsWholeType, ColorBean colorBean) {
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
            } else if (type == TouchEffectsManager.STATE_TYPE) {
                view = parseTypeView(TouchEffectsWholeType.STATE,parent,name, context, attrs);
            } else if (type == TouchEffectsManager.SHAKE_TYPE) {
                view = parseTypeView(TouchEffectsSingleType.SHAKE,parent,name, context, attrs);
            } else {
                view = parseTypeView(TouchEffectsWholeType.SCALE,parent,name, context, attrs);
            }
        }else{
            if(TouchEffectsManager.getViewTypes().get(name) != null){
                view = parseTypeView(TouchEffectsManager.getViewTypes().get(name),
                        parent,name,context,attrs);
            }else{
                view = parseTypeView(mTouchEffectsWholeType, parent,name,context,attrs);
            }

        }
        return view;
    }

    private View parseTypeView(TouchEffectsType type, View parent, String name, Context context, AttributeSet attrs){
        View view = parseEffectsView(parent,type,name,context,attrs);
//        if(type instanceof TouchEffectsSingleType){
//
//        }else{
//            TouchEffectsWholeType wholeType = (TouchEffectsWholeType) type;
//
//            switch (wholeType){
//                case SCALE:
//                    view = parseEffectsView(parent,name,context,attrs);
//                    break;
//                case RIPPLE:
//                    view = parseEffectsView(parent,name, context, attrs);
//                    break;
//                case STATE:
//                    view = parseEffectsView(parent,name, context, attrs);
//                    break;
//            }
//        }

        return view;
    }

    /**
     * 解析缩放模式下的View
     * @param name
     * @param context
     * @param attrs
     * @return
     */
    private View parseEffectsView(View parent, TouchEffectsType wholeType,String name, Context context, AttributeSet attrs){
//        if(parent instanceof RecyclerView || parent instanceof ListView){//如果是列表中，默认不适合scale，会添加水波纹
//            return parseRippleView(name,context,attrs);
//        }
        return findSystemViewChange(name,wholeType,context,attrs);
    }

//    private View parseRippleView(String name, Context context, AttributeSet attrs){
//        View view = getSystemView(name,context,attrs);
//        if(view == null){
//            return view;
//        }
//        Drawable drawable = getRippleDrawable(context,attrs);
//        if(drawable != null){
//            view.setBackground(drawable);
//        }
//        return view;
//    }
//
//    private View parseStateView(String name, Context context, AttributeSet attrs){
//        View view = getSystemView(name,context,attrs);
//        if(view == null){
//            return view;
//        }
//        Drawable drawable = getStateDrawable(context,attrs);
//        if(drawable != null){
//            view.setBackground(drawable);
//        }
//        return view;
//    }

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
            }
        }

        return null;
    }

//    private Drawable getRippleDrawable(Context context, AttributeSet attrs){
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
//        int pressedColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_pressed_color, mColorBean != null? mColorBean.getPressedColor():0);
//        int normalColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_normal_color, mColorBean != null? mColorBean.getNormalColor():0);
//        float radius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_radius,0);
//        float topLeftRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_top_left_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)? mColorBean.getRadiusBean().getTopLeftRadius():radius):radius);
//        float topRightRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_top_right_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)? mColorBean.getRadiusBean().getTopRightRadius():radius):radius);
//        float bottomLeftRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_bottom_left_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)? mColorBean.getRadiusBean().getBottomLeftRadius():radius):radius);
//        float bottomRightRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_bottom_right_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)? mColorBean.getRadiusBean().getBottomRightRadius():radius):radius);
//        int[][] stateList = new int[][]{
//                new int[]{android.R.attr.state_pressed},
//                new int[]{android.R.attr.state_focused},
//                new int[]{android.R.attr.state_activated},
//                new int[]{}
//        };
//        int[] stateColorList = new int[]{
//                pressedColor,
//                pressedColor,
//                pressedColor,
//                pressedColor
//        };
//        if(pressedColor != 0){
//            ShapeDrawable maskDrawable = new ShapeDrawable();
//            ShapeDrawable contentDrawable = new ShapeDrawable();
//            if(checkZero(radius,topLeftRadius,topRightRadius,bottomLeftRadius,bottomRightRadius)){
//                float[] outRadius;
//                if(radius != 0){
//                    outRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
//                }else{
//                    outRadius = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
//                            bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
//                }
//                RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);
//                maskDrawable.setShape(roundRectShape);
//                contentDrawable.setShape(roundRectShape);
//            }
//            maskDrawable.getPaint().setColor(pressedColor);
//            maskDrawable.getPaint().setStyle(Paint.Style.FILL);
//
//            contentDrawable.getPaint().setColor(normalColor);
//            contentDrawable.getPaint().setStyle(Paint.Style.FILL);
//            ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);
//            //contentDrawable实际是默认初始化时展示的；maskDrawable 控制了rippleDrawable的范围
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                return new RippleDrawable(colorStateList, contentDrawable, maskDrawable);
//            }else{
//                StateListDrawable selector = new StateListDrawable();
//                selector.addState(new int[]{android.R.attr.state_pressed}, maskDrawable);
//                selector.addState(new int[]{android.R.attr.state_focused}, maskDrawable);
//                selector.addState(new int[]{android.R.attr.state_enabled}, contentDrawable);
//                return selector;
//            }
//        }
//        return null;
//    }
//
//    private Drawable getStateDrawable(Context context, AttributeSet attrs){
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
//        int pressedColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_pressed_color,mColorBean != null?mColorBean.getPressedColor():0);
//        int normalColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_normal_color,mColorBean != null?mColorBean.getNormalColor():0);
//        float radius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_radius,0);
//        float topLeftRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_top_left_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)?mColorBean.getRadiusBean().getTopLeftRadius():radius):radius);
//        float topRightRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_top_right_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)?mColorBean.getRadiusBean().getTopRightRadius():radius):radius);
//        float bottomLeftRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_bottom_left_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)?mColorBean.getRadiusBean().getBottomLeftRadius():radius):radius);
//        float bottomRightRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_bottom_right_radius,radius==0?
//                ((mColorBean != null && mColorBean.getRadiusBean() != null)?mColorBean.getRadiusBean().getBottomRightRadius():radius):radius);
//        if(pressedColor != 0 && normalColor != 0){
//            ShapeDrawable maskDrawable = new ShapeDrawable();
//            ShapeDrawable contentDrawable = new ShapeDrawable();
//            if(checkZero(radius,topLeftRadius,topRightRadius,bottomLeftRadius,bottomRightRadius)){
//                float[] outRadius;
//                if(radius != 0){
//                    outRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
//                }else{
//                    outRadius = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
//                            bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
//                }
//                RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);
//                maskDrawable.setShape(roundRectShape);
//                contentDrawable.setShape(roundRectShape);
//            }
//            maskDrawable.getPaint().setColor(pressedColor);
//            maskDrawable.getPaint().setStyle(Paint.Style.FILL);
//
//            contentDrawable.getPaint().setColor(normalColor);
//            contentDrawable.getPaint().setStyle(Paint.Style.FILL);
//            StateListDrawable selector = new StateListDrawable();
//            selector.addState(new int[]{android.R.attr.state_pressed}, maskDrawable);
//            selector.addState(new int[]{android.R.attr.state_focused}, maskDrawable);
//            selector.addState(new int[]{android.R.attr.state_enabled}, contentDrawable);
//            return selector;
//        }
//        return null;
//    }

//    private View getSystemView(String name, Context context, AttributeSet attrs){
//        View view = null;
//        switch (name) {
//            case "TextView":
//                view = new AppCompatTextView(context, attrs);
//                break;
//            case "ImageView":
//                view = new AppCompatImageView(context, attrs);
//                break;
//            case "Button":
//                view = new AppCompatButton(context, attrs);
//                break;
//            case "EditText":
//                view = new AppCompatEditText(context, attrs);
//                break;
//            case "Spinner":
//                view = new AppCompatSpinner(context, attrs);
//                break;
//            case "ImageButton":
//                view = new AppCompatImageButton(context, attrs);
//                break;
//            case "CheckBox":
//                view = new AppCompatCheckBox(context, attrs);
//                break;
//            case "RadioButton":
//                view = new AppCompatRadioButton(context, attrs);
//                break;
//            case "CheckedTextView":
//                view = new AppCompatCheckedTextView(context, attrs);
//                break;
//            case "AutoCompleteTextView":
//                view = new AppCompatAutoCompleteTextView(context, attrs);
//                break;
//            case "MultiAutoCompleteTextView":
//                view = new AppCompatMultiAutoCompleteTextView(context, attrs);
//                break;
//            case "RatingBar":
//                view = new AppCompatRatingBar(context, attrs);
//                break;
//            case "SeekBar":
//                view = new AppCompatSeekBar(context, attrs);
//                break;
//            case "FrameLayout":
//                view = new FrameLayout(context, attrs);
//                break;
//            case "LinearLayout":
//                view = new LinearLayout(context, attrs);
//                break;
//            case "RelativeLayout":
//                view = new RelativeLayout(context, attrs);
//                break;
//            case "android.support.constraint.ConstraintLayout":
//                view = new ConstraintLayout(context,attrs);
//                break;
//                default:
//                    for (int i = 0; i < sClassPrefixList.length; i++) {
//                        view = createView(sClassPrefixList[i] + name, context, attrs);
//                        if (view != null) {//不为空说明系统中包含这个包名下的view
//                            return view;
//                        }
//                    }
//                    break;
//        }
//
//        return view;
//    }
//
//    /**
//     * 检查是否有值不为0
//     * @param values
//     * @return true：有值不为0，false：全部值为0
//     */
//    private boolean checkZero(float... values){
//        for(float value:values){
//            if(value != 0){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 拿到该view的实例
//     *
//     * @param name
//     * @param context
//     * @param attrs
//     * @return
//     */
//    private View createView(String name, Context context, AttributeSet attrs) {
//
//        try {
//            Class<? extends View> aClass = (Class<? extends View>) context.getClassLoader().loadClass(name);
//            Constructor<? extends View> constructor = aClass.getConstructor(new Class[]{Context.class, AttributeSet.class});
//            return constructor.newInstance(context, attrs);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
