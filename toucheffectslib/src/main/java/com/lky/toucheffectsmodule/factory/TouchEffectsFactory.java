package com.lky.toucheffectsmodule.factory;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;

public class TouchEffectsFactory {
    public static void initTouchEffects(@NonNull Activity activity){
        LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),new TouchEffectsInflaterFactory());
    }
}
