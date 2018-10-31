package com.lovelz.lzlearncenter.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.thread.know.ThreadKnowActivity;
import com.lovelz.lzlearncenter.thread.synchronization.ThreadSynchronizationActivity;

/**
 * @author lovelz
 * @date on 2018/10/23.
 */
public class ThreadPracticeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_practice);

        findViewById(R.id.enter_know_thread).setOnClickListener(this);
        findViewById(R.id.enter_thread_synchronization).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_know_thread:
                startActivity(new Intent(this, ThreadKnowActivity.class));
                break;
            case R.id.enter_thread_synchronization:
                startActivity(new Intent(this, ThreadSynchronizationActivity.class));
                break;
        }
    }
}
