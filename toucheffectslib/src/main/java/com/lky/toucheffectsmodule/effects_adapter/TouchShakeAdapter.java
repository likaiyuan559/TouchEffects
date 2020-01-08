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

public class TouchShakeAdapter extends EffectsAdapter {

    private float mTranslateValue;

    @Override
    public void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchEffectsView);
        mAnimationDuration = ta.getResourceId(R.styleable.TouchEffectsView_animation_duration,150);
    }

    @Override
    public void runAnimator(View view, Canvas canvas) {
        canvas.translate(view.getWidth() * mTranslateValue,0);
    }

    @Override
    public void dispatchDraw(View view, Canvas canvas) {

    }

    @Override
    public void drawForeground(View view, Canvas canvas) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent, View.OnClickListener onClickListener,View.OnLongClickListener onLongClickListener) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                engineAnimator(view);
                break;
        }
        return false;
    }

    @Override
    protected Animator createEngineAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,-0.1f,0f,0.1f,0f,-0.1f,0f);
        valueAnimator.setDuration(mAnimationDuration);
        valueAnimator.addUpdateListener(animation -> {
            mTranslateValue = (float) animation.getAnimatedValue();
            view.invalidate();
        });
        return valueAnimator;
    }

    @Override
    protected Animator createExtinctAnimator(View view) {
        return null;
    }
}
