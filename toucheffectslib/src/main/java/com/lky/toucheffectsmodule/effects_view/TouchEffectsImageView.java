package com.lky.toucheffectsmodule.effects_view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;


/**
 * Created by lky on 2018/8/23
 */
public class TouchEffectsImageView extends AppCompatImageView {

    private EffectsAdapter mEffectsAdapter;

    public TouchEffectsImageView(Context context) {
        this(context,null);
    }

    public TouchEffectsImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEffectsImageView(Context context, @Nullable AttributeSet attrs,EffectsAdapter effectsAdapter) {
        super(context, attrs,0);
        mEffectsAdapter = effectsAdapter;
        mEffectsAdapter.initAttr(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mEffectsAdapter.measuredSize(getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mEffectsAdapter.runAnimator(this,canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mOnClickListener == null && mOnLongClickListener == null || !isEnabled()){
            return super.onTouchEvent(event);
        }
        return mEffectsAdapter.onTouch(this,event,mOnClickListener,mOnLongClickListener);
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
            mEffectsAdapter.createLongClick(this,mOnLongClickListener);
        }
    }

}
