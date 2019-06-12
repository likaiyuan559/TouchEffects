package com.lky.toucheffectsmodule.factory;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.proxy.TouchEffectsCreateViewPageSubject;
import com.lky.toucheffectsmodule.proxy.TouchEffectsViewProxy;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class TouchEffectsInflaterFactory implements LayoutInflater.Factory2 {

    private TouchEffectsWholeType mTouchEffectsWholeType;
    private TouchEffectsViewProxy mTouchEffectsViewProxy;

    public TouchEffectsInflaterFactory() {
    }

    public TouchEffectsInflaterFactory(TouchEffectsWholeType touchEffectsWholeType) {
        mTouchEffectsWholeType = touchEffectsWholeType;
        mTouchEffectsViewProxy = new TouchEffectsViewProxy(new TouchEffectsCreateViewPageSubject(mTouchEffectsWholeType));
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if(mTouchEffectsViewProxy != null){
            return mTouchEffectsViewProxy.createView(parent,name,context,attrs);
        }
        return TouchEffectsManager.getViewSubject().createView(parent,name,context,attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if(mTouchEffectsViewProxy != null){
            return mTouchEffectsViewProxy.createView(null,name,context,attrs);
        }
        return TouchEffectsManager.getViewSubject().createView(null,name,context,attrs);
    }
}
