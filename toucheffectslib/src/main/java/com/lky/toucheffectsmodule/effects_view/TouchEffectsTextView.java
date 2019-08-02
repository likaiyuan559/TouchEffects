package com.lky.toucheffectsmodule.effects_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;
import com.lky.toucheffectsmodule.effects_proxy.BaseEffectsProxy;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by lky on 2018/8/27
 */
public class TouchEffectsTextView extends AppCompatTextView {

    private BaseEffectsProxy mEffectsProxy;

    public TouchEffectsTextView(Context context) {
        this(context,null);
    }

    public TouchEffectsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEffectsTextView(Context context, @Nullable AttributeSet attrs, BaseEffectsProxy effectsProxy) {
        super(context, attrs,0);
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
