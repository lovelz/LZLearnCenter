package com.lovelz.lzlearncenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lovelz.lzlearncenter.annotate.GameFlavourActivity;
import com.lovelz.lzlearncenter.design.DesignActivity;
import com.lovelz.lzlearncenter.di.DiTestActivity;
import com.lovelz.lzlearncenter.http.HttpActivity;
import com.lovelz.lzlearncenter.ipc.IPCSimulateActivity;
import com.lovelz.lzlearncenter.memory.MemoryOptimizeActivity;
import com.lovelz.lzlearncenter.recyclerview.AdvancedRecyclerViewActivity;
import com.lovelz.lzlearncenter.thread.ThreadPracticeActivity;
import com.lovelz.lzlearncenter.view.ViewActivity;

import androidx.appcompat.app.AppCompatActivity;

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
        findViewById(R.id.enter_memory_optimize).setOnClickListener(this);
        findViewById(R.id.enter_annotate).setOnClickListener(this);
        findViewById(R.id.enter_di).setOnClickListener(this);
        findViewById(R.id.enter_advanced_recycler).setOnClickListener(this);
        findViewById(R.id.enter_design).setOnClickListener(this);
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
//                intent = new Intent(this, CustomViewActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "已经修改了", Toast.LENGTH_SHORT).show();
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
            case R.id.enter_memory_optimize:
                intent = new Intent(this, MemoryOptimizeActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_annotate:
                intent = new Intent(this, GameFlavourActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_di:
                intent = new Intent(this, DiTestActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_advanced_recycler:
                intent = new Intent(this, AdvancedRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.enter_design:
                intent = new Intent(this, DesignActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
