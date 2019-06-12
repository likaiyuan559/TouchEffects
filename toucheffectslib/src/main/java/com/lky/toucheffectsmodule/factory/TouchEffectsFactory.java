package com.lky.toucheffectsmodule.factory;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;

import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class TouchEffectsFactory {
    public static void initTouchEffects(@NonNull Activity activity){
        LayoutInflaterCompat.setFactory2(activity.getLayoutInflater(),new TouchEffectsInflaterFactory());
    }

    public static void initTouchEffects(@NonNull Activity activity, TouchEffectsWholeType touchEffectsWholeType){
        LayoutInflaterCompat.setFactory2(activity.getLayoutInflater(),new TouchEffectsInflaterFactory(touchEffectsWholeType));
    }
}
