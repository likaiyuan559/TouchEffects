package com.lky.toucheffectsviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class TouchEffectsMainActivity extends TouchEffectsBaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_effects_activity_main);
        findViewById(R.id.my_test_txt).setOnClickListener(this);
        findViewById(R.id.my_test_txt_1).setOnClickListener(this);
        findViewById(R.id.my_test_txt_2).setOnClickListener(this);
        findViewById(R.id.my_test_img).setOnClickListener(this);
        findViewById(R.id.my_test_ll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_none:
                TouchEffectsManager.build(TouchEffectsWholeType.NONE)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_scale:
                TouchEffectsManager.build(TouchEffectsWholeType.SCALE)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_ripple:
                TouchEffectsManager.build(TouchEffectsWholeType.RIPPLE)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_ripple_1:
                TouchEffectsManager.build(TouchEffectsWholeType.RIPPLE_1)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_state:
                TouchEffectsManager.build(TouchEffectsWholeType.STATE)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_single:
                Intent intent = new Intent(this,TouchEffectsPersonalizedSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list:
                Intent intent1 = new Intent(this,TouchEffectsListActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
