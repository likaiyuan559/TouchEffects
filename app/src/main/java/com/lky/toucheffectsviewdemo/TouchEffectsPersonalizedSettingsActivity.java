package com.lky.toucheffectsviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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
    }

    @Override
    public void onClick(View v) {

    }
}
