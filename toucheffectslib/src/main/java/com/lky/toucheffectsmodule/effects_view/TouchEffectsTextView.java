package com.lky.toucheffectsmodule.effects_view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.lky.toucheffectsmodule.R;
import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;


/**
 * Created by lky on 2018/8/27
 */
public class TouchEffectsTextView extends AppCompatTextView {

    private EffectsAdapter mEffectsAdapter;

    public TouchEffectsTextView(Context context) {
        this(context,null);
    }

    public TouchEffectsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEffectsTextView(Context context, @Nullable AttributeSet attrs,EffectsAdapter effectsAdapter) {
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
        if(mOnClickListener == null && mOnLongClickListener == null){
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
