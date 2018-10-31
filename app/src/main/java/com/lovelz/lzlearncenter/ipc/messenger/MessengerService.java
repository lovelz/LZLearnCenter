package com.lovelz.lzlearncenter.ipc.messenger;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author lovelz
 * @date on 2018/10/12.
 */
public class MessengerService extends Service {

    public static final String TAG = "IPCMessenger";
    public static final int MSG_FROM_CLIENT = 0x01;
    public static final int MSG_FROM_SERVICE = 0x02;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.i(TAG, "收到客户端发来的信息-->  " + msg.getData().get("msg"));
                    Messenger mMessenger = msg.replyTo;
                    Message mMessage = Message.obtain(null, MSG_FROM_SERVICE);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("rep", "收到收到，请求支援");
                    mMessage.setData(mBundle);
                    try {
                        mMessenger.send(mMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
