package com.lky.toucheffectsmodule.factory;

import android.app.Activity;

import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;

public class TouchEffectsFactory {
    public static void initTouchEffects(@NonNull Activity activity){
        LayoutInflaterCompat.setFactory2(activity.getLayoutInflater(),new TouchEffectsInflaterFactory());
    }

    public static void initTouchEffects(@NonNull Activity activity, TouchEffectsWholeType touchEffectsWholeType){
        LayoutInflaterCompat.setFactory2(activity.getLayoutInflater(),new TouchEffectsInflaterFactory(touchEffectsWholeType));
    }
}
