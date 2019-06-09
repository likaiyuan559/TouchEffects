package com.lky.toucheffectsmodule.proxy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lky.toucheffectsmodule.impl.TouchEffectsViewSubject;

public class TouchEffectsViewProxy implements TouchEffectsViewSubject {

    private TouchEffectsViewSubject mTouchEffectsViewSubject;


    public TouchEffectsViewProxy(TouchEffectsViewSubject touchEffectsViewSubject) {
        mTouchEffectsViewSubject = touchEffectsViewSubject;
    }

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        return mTouchEffectsViewSubject.createView(parent,name,context,attrs);
    }
}
