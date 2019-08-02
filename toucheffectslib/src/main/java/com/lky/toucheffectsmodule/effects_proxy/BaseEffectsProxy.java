package com.lky.toucheffectsmodule.effects_proxy;

import android.content.Context;
import android.util.AttributeSet;

import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;

//后期迭代可能会出现混合模式
public class BaseEffectsProxy {

    protected EffectsAdapter mAdapter;
    protected AttributeSet mAttributeSet;
    protected Context mContext;

    public BaseEffectsProxy(EffectsAdapter adapter) {
        mAdapter = adapter;
    }

    public void replaceEffectAdapter(EffectsAdapter adapter){
        mAdapter = adapter;
    }

    public EffectsAdapter getAdapter() {
        return mAdapter;
    }

    public void initAttr(Context context, AttributeSet attrs){
        mContext = context;
        mAttributeSet = attrs;
        if(mAdapter != null){
            mAdapter.initAttr(context,attrs);
        }
    }

    public void measuredSize(int measuredWidth, int measuredHeight) {
        if(mAdapter != null){
            mAdapter.measuredSize(measuredWidth,measuredHeight);
        }
    }
}
