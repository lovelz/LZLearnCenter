package com.lovelz.lzlearncenter.ipc.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * @author lovelz
 * @date on 2018/10/24.
 */
public class LooperThread extends Thread {

    public Handler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        Looper.loop();
    }
}
