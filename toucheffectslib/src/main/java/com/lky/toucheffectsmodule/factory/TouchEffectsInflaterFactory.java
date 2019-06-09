package com.lky.toucheffectsmodule.factory;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lky.toucheffectsmodule.TouchEffectsManager;

public class TouchEffectsInflaterFactory implements LayoutInflater.Factory2 {


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null,name,context,attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return TouchEffectsManager.getViewSubject().createView(parent,name,context,attrs);
    }

}
