package com.lky.toucheffectsviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;

public class TouchEffectsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TouchEffectsFactory.initTouchEffects(this);
        super.onCreate(savedInstanceState);
    }
}
