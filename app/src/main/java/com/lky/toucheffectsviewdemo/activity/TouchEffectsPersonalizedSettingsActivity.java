package com.lky.toucheffectsviewdemo.activity;

import android.os.Bundle;
import android.view.View;

import com.lky.toucheffectsviewdemo.R;

import androidx.annotation.Nullable;

public class TouchEffectsPersonalizedSettingsActivity extends TouchEffectsBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_effects_activity_personalized_settings);
        findViewById(R.id.single_none_txt).setOnClickListener(this);
        findViewById(R.id.single_scale_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_round_txt).setOnClickListener(this);
        findViewById(R.id.single_state_txt).setOnClickListener(this);
        findViewById(R.id.single_state_round_txt).setOnClickListener(this);
        findViewById(R.id.single_shake_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_color_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_1_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_round_1_txt).setOnClickListener(this);
        findViewById(R.id.single_ripple_color_1_txt).setOnClickListener(this);
        findViewById(R.id.single_state_color_txt).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
