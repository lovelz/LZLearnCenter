package com.lovelz.lzlearncenter.thread.synchronization;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lovelz.lzlearncenter.R;

/**
 * 线程同步
 * 1、使用synchronization或者Lock
 *
 * 2、使用volatile关键字
 *
 * @author lovelz
 * @date on 2018/10/23.
 */
public class ThreadSynchronizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread_synchronization);
    }
}
