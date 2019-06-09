package com.lky.toucheffectsmodule.factory;


import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lky.toucheffectsmodule.TouchEffectsManager;

public class TouchEffectsInflaterFactory implements LayoutInflaterFactory {


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return TouchEffectsManager.getViewSubject().createView(parent,name,context,attrs);
    }
}
