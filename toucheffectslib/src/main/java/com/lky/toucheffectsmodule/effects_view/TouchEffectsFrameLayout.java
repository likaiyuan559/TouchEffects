package com.lky.toucheffectsmodule.effects_view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;
import com.lky.toucheffectsmodule.effects_proxy.BaseEffectsProxy;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;


/**
 * Created by lky on 2018/9/4
 */
public class TouchEffectsFrameLayout extends ContentFrameLayout {

    private BaseEffectsProxy mEffectsProxy;


    public TouchEffectsFrameLayout(Context context) {
        this(context,null);
    }

    public TouchEffectsFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEffectsFrameLayout(Context context, @Nullable AttributeSet attrs,BaseEffectsProxy effectsProxy) {
        super(context, attrs,0);
        setWillNotDraw(false);
        mEffectsProxy = effectsProxy;
        mEffectsProxy.initAttr(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mEffectsProxy.measuredSize(getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mEffectsProxy.getAdapter().runAnimator(this,canvas);
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mEffectsProxy.getAdapter().dispatchDraw(this,canvas);
        }
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        mEffectsProxy.getAdapter().drawForeground(this,canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mOnClickListener == null && mOnLongClickListener == null || !isEnabled()){
            return super.onTouchEvent(event);
        }
        return mEffectsProxy.getAdapter().onTouch(this,event,mOnClickListener,mOnLongClickListener);
    }


    public OnClickListener mOnClickListener;
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOnClickListener = l;
    }

    public OnLongClickListener mOnLongClickListener;
    @Override
    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
        if(mOnLongClickListener != null){
            mEffectsProxy.getAdapter().createLongClick(this,mOnLongClickListener);
        }
    }
}
