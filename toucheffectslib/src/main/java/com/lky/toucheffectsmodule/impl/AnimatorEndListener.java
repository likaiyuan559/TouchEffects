package com.lky.toucheffectsmodule.impl;

import android.animation.Animator;

public abstract class AnimatorEndListener implements Animator.AnimatorListener {

    public abstract void onAnimatorEnd(Animator animation);


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        onAnimatorEnd(animation);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
