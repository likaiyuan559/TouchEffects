package com.lky.toucheffectsmodule.effects_proxy;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.bean.extra.BaseExtraBean;
import com.lky.toucheffectsmodule.bean.extra.ExtraAspectRatioBean;
import com.lky.toucheffectsmodule.effects_adapter.EffectsAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchRipple1Adapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchRippleAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchScaleAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchShakeAdapter;
import com.lky.toucheffectsmodule.effects_adapter.TouchStateAdapter;
import com.lky.toucheffectsmodule.types.TouchEffectsExtraType;
import com.lky.toucheffectsmodule.types.TouchEffectsSingleType;
import com.lky.toucheffectsmodule.types.TouchEffectsType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class AspectRatioEffectsProxy extends BaseEffectsProxy {


    public AspectRatioEffectsProxy(EffectsAdapter adapter) {
        super(adapter);
    }

    @Override
    public void measuredSize(int measuredWidth, int measuredHeight) {
        BaseExtraBean extraBean = TouchEffectsManager.getExtraTypeMap().get(TouchEffectsExtraType.AspectRatio);
        boolean isChange = false;
        if(extraBean != null){
            ExtraAspectRatioBean aspectRatioBean = (ExtraAspectRatioBean) extraBean;
            float width = aspectRatioBean.getWidth();
            float height = aspectRatioBean.getHeight();
            //启动了大小判断，当宽或高大小超过，就转换模式
            if((width > 1f && measuredWidth > width) || (height > 1f && measuredHeight > height)){
                isChange = true;
            }else if(width <= 1f && height <= 1f){
                //比例判断
                if(width > height && width/height < measuredWidth/(float)measuredHeight){
                    isChange = true;
                }else if(height > width && width/height > measuredWidth/(float)measuredHeight){
                    isChange = true;
                }
            }
        }
        if(isChange){
            mAdapter = getAdapter(extraBean.getWholeType());
            mAdapter.initAttr(mContext,mAttributeSet);
            mAdapter.measuredSize(measuredWidth,measuredHeight);
        }else{
            super.measuredSize(measuredWidth, measuredHeight);
        }

    }


    private EffectsAdapter getAdapter(TouchEffectsType type){
        if(type instanceof TouchEffectsSingleType){
            TouchEffectsSingleType singleType = (TouchEffectsSingleType) type;
            switch (singleType){
                case SHAKE:
                    return new TouchShakeAdapter();
            }
        }else {
            TouchEffectsWholeType wholeType = (TouchEffectsWholeType) type;
            switch (wholeType){
                case SCALE:
                    return new TouchScaleAdapter(TouchEffectsManager.getScaleBean());
                case RIPPLE:
                    return new TouchRippleAdapter(TouchEffectsManager.getColorBean());
                case STATE:
                    return new TouchStateAdapter(TouchEffectsManager.getColorBean());
                case RIPPLE_1:
                    return new TouchRipple1Adapter(TouchEffectsManager.getColorBean());
            }
        }

        return null;
    }
}
