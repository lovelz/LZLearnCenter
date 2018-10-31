package com.lovelz.lzlearncenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lovelz.lzlearncenter.http.HttpActivity;
import com.lovelz.lzlearncenter.ipc.IPCSimulateActivity;
import com.lovelz.lzlearncenter.thread.ThreadPracticeActivity;
import com.lovelz.lzlearncenter.view.CustomViewActivity;
import com.lovelz.lzlearncenter.view.ViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.enter_view).setOnClickListener(this);
        findViewById(R.id.enter_custom_view).setOnClickListener(this);
        findViewById(R.id.enter_ipc).setOnClickListener(this);
        findViewById(R.id.enter_network).setOnClickListener(this);
        findViewById(R.id.enter_thread).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.enter_view:
                intent = new Intent(this, ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_custom_view:
                intent = new Intent(this, CustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_ipc:
                intent = new Intent(this, IPCSimulateActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_network:
                intent = new Intent(this, HttpActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_thread:
                intent = new Intent(this, ThreadPracticeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
