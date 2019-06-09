package com.lky.toucheffectsviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.menu_state:
                TouchEffectsManager.build(TouchEffectsWholeType.STATE)
                        .addViewType(TouchEffectsViewType.ALL);
                recreate();
                break;
            case R.id.menu_single:
                Intent intent = new Intent(this,PersonalizedSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
