package com.lky.toucheffectsviewdemo;

import android.app.Application;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class TouchEffectsApplication extends Application {

    static {
        TouchEffectsManager.build(TouchEffectsWholeType.SCALE)
                .addViewType(TouchEffectsViewType.ALL)
                .setListWholeType(TouchEffectsWholeType.RIPPLE)
                .setAspectRatioType(4f,TouchEffectsWholeType.RIPPLE);//宽高比大于4时启动水波纹

    }
}
