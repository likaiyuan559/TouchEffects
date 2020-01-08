package com.lky.toucheffectsmodule.effects_adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.lky.toucheffectsmodule.R;
import com.lky.toucheffectsmodule.bean.ColorBean;
import com.lky.toucheffectsmodule.impl.AnimatorEndListener;

public class TouchRipple1Adapter extends EffectsAdapter {
    private final int TRANSPARENT_COLOR = 0x00000000;
    private int mPressedColor;
    private int mNormalColor;
    private int mCurrentColor;

    private float mRadius;
    private ColorBean mColorBean;

    private float mCircleRadius;
    private float mTouchX,mTouchY;
    private Paint mPaint;
    private RectF mRect;
    private Path mPath;
    private float[] mRadiusArray;


    public TouchRipple1Adapter(ColorBean colorBean) {
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
        if(mRadius != 0){
            mRadiusArray = new float[8];
            mRadiusArray[0] = mRadius;
            mRadiusArray[1] = mRadius;
            mRadiusArray[2] = mRadius;
            mRadiusArray[3] = mRadius;
            mRadiusArray[4] = mRadius;
            mRadiusArray[5] = mRadius;
            mRadiusArray[6] = mRadius;
            mRadiusArray[7] = mRadius;
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    public void measuredSize(int width, int height) {
        super.measuredSize(width, height);
        if(mRadiusArray != null){
            mPath = new Path();
            mPath.addRoundRect(new RectF(0, 0, width, height), mRadiusArray, Path.Direction.CW);
        }
        mRect = new RectF(0,0,width,height);

    }

    @Override
    public void runAnimator(View view, Canvas canvas) {

    }

    @Override
    public void dispatchDraw(View view, Canvas canvas) {
        if(mPath != null){
            canvas.clipPath(mPath);
        }
        mPaint.setColor(mCurrentColor);
        canvas.drawCircle(mTouchX,mTouchY,mCircleRadius,mPaint);
    }

    @Override
    public void drawForeground(View view, Canvas canvas) {
        dispatchDraw(view,canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event, View.OnClickListener onClickListener,View.OnLongClickListener onLongClickListener) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mCircleRadius = 0;
                mCurrentColor = mPressedColor;
                mTouchX = event.getX();
                mTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(pointInView(view,event.getX(), event.getY(),0)){
                    mTouchX = event.getX();
                    mTouchY = event.getY();
                }
                break;
        }
        return touchView(view,event,onClickListener);
    }


    @Override
    protected void engineAnimator(View view) {
        if(mEngineAnimator != null){
            mEngineAnimator.setDuration((long) getTimeScale(view));
        }
        super.engineAnimator(view);
    }

    @Override
    protected void extinctAnimator(View view){
        if(mExtinctAnimator == null){
            mExtinctAnimator = createExtinctAnimator(view);
        }
        if(mExtinctAnimator != null){
            if(mExtinctAnimator.isRunning()){
                mExtinctAnimator.cancel();
            }
            mExtinctAnimator.start();
        }
    }

    @Override
    protected Animator createEngineAnimator(View view) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,Math.max(view.getWidth(),view.getHeight()) * 1.42f);
        valueAnimator.setDuration((long) getTimeScale(view));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCircleRadius = value;
                view.invalidate();
            }
        });
        return valueAnimator;
    }

    @Override
    protected Animator createExtinctAnimator(View view) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();//渐变色计算类
        ValueAnimator valueAnimator;
        valueAnimator = ValueAnimator.ofFloat(1f,0f);
        valueAnimator.setDuration(450);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentColor = (int) (argbEvaluator.evaluate((float)animation.getAnimatedValue(), TRANSPARENT_COLOR, mNormalColor));
                view.invalidate();
            }
        });
        return valueAnimator;
    }

    private float getTimeScale(View view){
        float sizeX = Math.abs(mTouchX - view.getWidth()/2f);
        float sizeY = Math.abs(mTouchY - view.getHeight()/2f);
        float timeScale = 0f;
        if(sizeX != 0 && sizeY != 0){
            timeScale = Math.min(sizeX,sizeY)/(sizeX<sizeY?view.getWidth()/2f:view.getHeight()/2f);
        }
        return 450f - (200f * timeScale);
    }
}
