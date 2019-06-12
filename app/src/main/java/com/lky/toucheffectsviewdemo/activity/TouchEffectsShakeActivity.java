package com.lky.toucheffectsviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lky.toucheffectsviewdemo.R;

public class TouchEffectsShakeActivity extends TouchEffectsBaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_effects_shake_sample);
        findViewById(R.id.touch_effects_sample_txt).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_btn).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_img).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_ib).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_fl).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_ll).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_rl).setOnClickListener(this);
        findViewById(R.id.touch_effects_sample_cl).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }

}
