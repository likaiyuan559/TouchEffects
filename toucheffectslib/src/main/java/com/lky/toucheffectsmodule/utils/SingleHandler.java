package com.lky.toucheffectsmodule.utils;

import android.os.Handler;

/**
 * Created by lky on 2018/8/30
 */
public class SingleHandler extends Handler {

    private static SingleHandler mInstance;

    private SingleHandler() {
    }

    public static SingleHandler getInstance() {
        if (mInstance == null) {
            synchronized (SingleHandler.class) {
                if (mInstance == null) {
                    mInstance = new SingleHandler();
                }
            }
        }

        return mInstance;
    }

}
