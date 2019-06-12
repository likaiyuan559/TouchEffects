package com.lky.toucheffectsviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import com.lky.toucheffectsviewdemo.R;
import com.lky.toucheffectsviewdemo.bean.JumpBean;

import java.util.ArrayList;

public class TouchEffectsMainActivity extends TouchEffectsBaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_effects_activity_main);

        ArrayList<JumpBean> jumpArrayList = new ArrayList<>();
        jumpArrayList.add(new JumpBean("缩放效果示例",TouchEffectsScaleActivity.class));
        jumpArrayList.add(new JumpBean("水波纹效果示例",TouchEffectsRippleActivity.class));
        jumpArrayList.add(new JumpBean("水波纹1效果示例",TouchEffectsRipple1Activity.class));
        jumpArrayList.add(new JumpBean("渐变效果示例",TouchEffectsStateActivity.class));
        jumpArrayList.add(new JumpBean("抖动效果示例",TouchEffectsShakeActivity.class));
        jumpArrayList.add(new JumpBean("列表效果示例",TouchEffectsListActivity.class));
        jumpArrayList.add(new JumpBean("个性化设置示例",TouchEffectsPersonalizedSettingsActivity.class));

        RecyclerView recyclerView = findViewById(R.id.touch_effects_main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        RecyclerAdapter adapter = new RecyclerAdapter(jumpArrayList);
        recyclerView.setAdapter(adapter);


    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<JumpBean> mJumpArrayList;


        public RecyclerAdapter(ArrayList<JumpBean> jumpArrayList) {
            mJumpArrayList = jumpArrayList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.touch_effects_layout_list_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            bindHolder((ViewHolder)holder,position);
        }

        private void bindHolder(ViewHolder holder, int position) {
            ((TextView)holder.itemView).setText(mJumpArrayList.get(position).getTitleName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(TouchEffectsMainActivity.this,mJumpArrayList.get(position).getJumpClass()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mJumpArrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
