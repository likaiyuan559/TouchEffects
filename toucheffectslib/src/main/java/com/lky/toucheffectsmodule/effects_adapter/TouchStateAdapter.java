package com.lky.toucheffectsmodule.effects_adapter;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lky.toucheffectsmodule.R;
import com.lky.toucheffectsmodule.bean.ColorBean;

public class TouchStateAdapter extends EffectsAdapter {
    private final int TRANSPARENT_COLOR = 0x00000000;
    private int mPressedColor;
    private int mNormalColor;
    private int mCurrentColor;

    private float mRadius;
    private ColorBean mColorBean;

    private float mColorValue;
    private Paint mPaint;
    private RectF mRect;


    public TouchStateAdapter(ColorBean colorBean) {
        mColorBean = colorBean;
    }

    @Override
    public void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
        mPressedColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_pressed_color, mColorBean != null? mColorBean.getPressedColor():0);
        mNormalColor = ta.getColor(R.styleable.TouchEffectsView_touch_effects_normal_color, mColorBean != null? mColorBean.getNormalColor():0);
        if(mNormalColor == 0){
            mNormalColor = mPressedColor;
        }
        mRadius = ta.getDimension(R.styleable.TouchEffectsView_touch_effects_radius,0);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void measuredSize(int width, int height) {
        super.measuredSize(width, height);
        mRect = new RectF(0,0,width,height);

    }

    @Override
    public void runAnimator(View view, Canvas canvas) {

    }

    @Override
    public void dispatchDraw(View view, Canvas canvas) {
        mPaint.setColor(mCurrentColor);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRoundRect(mRect,mRadius,mRadius,mPaint);
    }

    @Override
    public void drawForeground(View view, Canvas canvas) {
        dispatchDraw(view,canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        return touchView(view,event,onClickListener);
    }

    @Override
    protected Animator createEngineAnimator(View view) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();//渐变色计算类
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,1f);
        valueAnimator.setDuration(850);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mColorValue = (float) animation.getAnimatedValue();
                mCurrentColor = (int) (argbEvaluator.evaluate(mColorValue, TRANSPARENT_COLOR, mPressedColor));
                view.invalidate();
            }
        });
        return valueAnimator;
    }

    @Override
    protected Animator createExtinctAnimator(View view) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();//渐变色计算类
        ValueAnimator valueAnimator;
        if(mColorValue < 0.5f){
            valueAnimator = ValueAnimator.ofFloat(mColorValue,0.8f,0.6f,0.4f,0.2f,0f);
        }else{
            valueAnimator = ValueAnimator.ofFloat(mColorValue,0f);
        }
        valueAnimator.setDuration(450);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mColorValue = (float) animation.getAnimatedValue();
                mCurrentColor = (int) (argbEvaluator.evaluate(mColorValue, TRANSPARENT_COLOR, mNormalColor));
                view.invalidate();
            }
        });
        return valueAnimator;
    }
}
