package com.lky.toucheffectsviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;

public class TouchEffectsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initTouchEffects();
        super.onCreate(savedInstanceState);
    }

    protected void initTouchEffects() {
        TouchEffectsFactory.initTouchEffects(this);
    }
}
