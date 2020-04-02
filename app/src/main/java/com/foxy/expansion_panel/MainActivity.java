package com.foxy.expansion_panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @OnClick(R.id.btnCustom)
    protected void onPanelCustom() {
        startActivity(new Intent(this, CustomActivity.class));
    }

    @OnClick(R.id.btnViewGroup)
    protected void onPanelViewGroup() {
        startActivity(new Intent(this, ViewGroupActivity.class));
    }

    @OnClick(R.id.btnCustom2)
    protected void onPanelCustom2() {
        startActivity(new Intent(this, Custom2Activity.class));
    }

    @OnClick(R.id.btnRecyclerView)
    protected void onPanelRecyclerView() {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
