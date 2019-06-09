package com.lky.toucheffectsviewdemo;

import android.app.Application;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class MyApplication extends Application {

    static {
        TouchEffectsManager.build(TouchEffectsWholeType.SCALE)
                .addViewType(TouchEffectsViewType.ALL);
    }
}
