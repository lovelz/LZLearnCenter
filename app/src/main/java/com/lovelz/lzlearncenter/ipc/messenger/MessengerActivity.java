package com.lovelz.lzlearncenter.ipc.messenger;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.lovelz.lzlearncenter.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2018/10/12.
 */
public class MessengerActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVICE:
                    Log.d(MessengerService.TAG, "收到服务端发来的信息-->  " + msg.getData().get("rep"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, messengerConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection messengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger mMessenger = new Messenger(service);
            Message mMessage = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
            Bundle mBundle = new Bundle();
            mBundle.putString("msg", "这里是客户端，服务端收到请回复");
            mMessage.setData(mBundle);

            mMessage.replyTo = new Messenger(mHandler);
            try {
                mMessenger.send(mMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
