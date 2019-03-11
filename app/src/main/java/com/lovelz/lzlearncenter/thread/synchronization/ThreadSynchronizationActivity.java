package com.lovelz.lzlearncenter.thread.synchronization;

import android.os.Bundle;

import com.lovelz.lzlearncenter.R;

import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread_synchronization);
    }
}
