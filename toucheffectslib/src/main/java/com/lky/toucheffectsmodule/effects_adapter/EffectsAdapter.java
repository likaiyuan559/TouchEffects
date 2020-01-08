package com.lky.toucheffectsmodule.effects_adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lky.toucheffectsmodule.utils.SingleHandler;

public abstract class EffectsAdapter {

    public abstract void initAttr(Context context, AttributeSet attrs);
    public abstract void runAnimator(View view, Canvas canvas);
    public abstract void dispatchDraw(View view, Canvas canvas);
    public abstract void drawForeground(View view, Canvas canvas);
    public abstract boolean onTouch(View view, MotionEvent motionEvent,
                                    View.OnClickListener onClickListener,
                                    View.OnLongClickListener onLongClickListener);
    protected abstract Animator createEngineAnimator(View view);
    protected abstract Animator createExtinctAnimator(View view);


    protected final int LONG_DURATION = 850;
    protected final int CLICK_DURATION = 100;
    protected int mAnimationDuration = 100;
    protected Animator mEngineAnimator;
    protected Animator mExtinctAnimator;
    protected boolean isPointLeaveView;
    protected boolean isLongClick;
    protected int mViewWidth,mViewHeight;
    protected Runnable mClickRunnable = null;
    protected Runnable mLongClickRunnable = null;

    public void measuredSize(int width,int height){
        mViewWidth = width;
        mViewHeight = height;
    }

    protected void engineAnimator(View view){
        if(mExtinctAnimator != null && mExtinctAnimator.isRunning()){
            mExtinctAnimator.cancel();
        }
        if(mEngineAnimator == null) {
            mEngineAnimator = createEngineAnimator(view);
        }
        if(mEngineAnimator != null){
            mEngineAnimator.start();
        }
    }


    protected void extinctAnimator(View view){
        if(mEngineAnimator != null && mEngineAnimator.isRunning()){
            mEngineAnimator.cancel();
        }
        if(mExtinctAnimator == null){
            mExtinctAnimator = createExtinctAnimator(view);
        }
        if(mExtinctAnimator != null){
            mExtinctAnimator.start();
        }
    }

    protected boolean touchView(View view, MotionEvent event, View.OnClickListener onClickListener){
        if(onClickListener == null){
            return false;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isPointLeaveView = false;
                isLongClick = false;
                engineAnimator(view);
                if(mLongClickRunnable != null){
                    SingleHandler.getInstance().postDelayed(mLongClickRunnable,LONG_DURATION);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!pointInView(view,event.getX(), event.getY(),0) && !isPointLeaveView){
                    isPointLeaveView = true;
                    extinctAnimator(view);
                    if(mLongClickRunnable != null){
                        isLongClick = false;
                        SingleHandler.getInstance().removeCallbacks(mLongClickRunnable);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(onClickListener != null && !isPointLeaveView && !isLongClick){
                    if(mClickRunnable == null){
                        createClick(view,onClickListener);
                    }
                    SingleHandler.getInstance().postDelayed(mClickRunnable,CLICK_DURATION);
//                    onClickListener.onClick(view);
                }
                //不能加break
            case MotionEvent.ACTION_CANCEL:
                if(!isPointLeaveView){
                    isPointLeaveView = true;
                    extinctAnimator(view);
                }
                if(mLongClickRunnable != null){
                    isLongClick = false;
                    SingleHandler.getInstance().removeCallbacks(mLongClickRunnable);
                }
                break;
        }
        return true;
    }

    public void createClick(View view, View.OnClickListener onClickListener){
        mClickRunnable = () -> {
            if(onClickListener != null){
                onClickListener.onClick(view);
            }
        };
    }

    public void createLongClick(View view, View.OnLongClickListener onLongClickListener){
        mLongClickRunnable = () -> {
            if(onLongClickListener != null){
                isLongClick = onLongClickListener.onLongClick(view);
            }
        };
    }


    protected boolean pointInView(View view, float localX, float localY, float slop) {
        return localX >= -slop && localY >= -slop && localX < ((view.getRight() - view.getLeft()) + slop) &&
                localY < ((view.getBottom() - view.getTop()) + slop);
    }

}
