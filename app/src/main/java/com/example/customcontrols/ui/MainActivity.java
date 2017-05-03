package com.example.customcontrols.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.customcontrols.R;
import com.example.customcontrols.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.CallBack {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initData();

        initRV();

        refreshRV();

    }

    private void refreshRV() {
        if (null != myRecyclerViewAdapter) {
            myRecyclerViewAdapter.setData(data);
            myRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<String> data;

    private void initRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        rv.setAdapter(myRecyclerViewAdapter);
    }


    private void initData() {
        data = new ArrayList<String>();
        data.add(getString(R.string.test1));
        data.add(getString(R.string.test2));
    }

    @Override
    public void rVOnItemClick(int position) {
        if (null != myRecyclerViewAdapter) {
            String str = myRecyclerViewAdapter.getItem(position);
            if (!TextUtils.isEmpty(str)) {
                startActivity(new Intent(MainActivity.this,CanvasRoundActivity.class));
                if (str.equals(getString(R.string.test1))) {
                } else if (str.equals(getString(R.string.test2))) {
                    startActivity(new Intent(MainActivity.this,CanvasAnimationActivity.class));
                }
            }
        }
    }
}
