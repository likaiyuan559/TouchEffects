package com.lky.toucheffectsviewdemo.activity;

import android.os.Bundle;

import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
