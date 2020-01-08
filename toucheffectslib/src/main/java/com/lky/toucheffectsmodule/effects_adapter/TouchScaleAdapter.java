package com.lky.toucheffectsmodule.effects_adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lky.toucheffectsmodule.R;
import com.lky.toucheffectsmodule.bean.ScaleBean;

public class TouchScaleAdapter extends EffectsAdapter {

    private float mShakeScale = 0.85f;
    private float mCurrentScaleX = 1.0f,mCurrentScaleY = 1.0f;

    public TouchScaleAdapter(ScaleBean scaleBean){
        if(scaleBean != null){
            mShakeScale = scaleBean.getShakeScale();
            mAnimationDuration = scaleBean.getAnimationDuration();
        }
    }

    @Override
    public void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
        mAnimationDuration = ta.getResourceId(R.styleable.TouchEffectsView_animation_duration,100);
        mShakeScale = ta.getFloat(R.styleable.TouchEffectsView_shake_view_scale,0.85f);
    }

    @Override
    public void runAnimator(View view,Canvas canvas) {
        canvas.scale(mCurrentScaleX,mCurrentScaleY,view.getMeasuredWidth()/2f,view.getMeasuredHeight()/2f);
    }

    @Override
    public void dispatchDraw(View view, Canvas canvas) {

    }

    @Override
    public void drawForeground(View view, Canvas canvas) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        return touchView(view,motionEvent,onClickListener);
    }

    @Override
    protected Animator createEngineAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, mShakeScale);
        valueAnimator.setDuration(mAnimationDuration);
        valueAnimator.addUpdateListener(animation -> {
            mCurrentScaleX = (float) animation.getAnimatedValue();
            mCurrentScaleY = mCurrentScaleX;
            view.invalidate();
        });
        return valueAnimator;
    }

    @Override
    protected Animator createExtinctAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mShakeScale,1.0f);
        valueAnimator.setDuration(mAnimationDuration);
        valueAnimator.addUpdateListener(animation -> {
            mCurrentScaleX = (float) animation.getAnimatedValue();
            mCurrentScaleY = mCurrentScaleX;
            view.invalidate();
        });
        return valueAnimator;
    }


}
