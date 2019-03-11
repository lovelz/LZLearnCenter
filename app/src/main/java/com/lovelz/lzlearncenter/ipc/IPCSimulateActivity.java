package com.lovelz.lzlearncenter.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.ipc.aidl.AIDLActivity;
import com.lovelz.lzlearncenter.ipc.handler.HandlerThreadActivity;
import com.lovelz.lzlearncenter.ipc.messenger.MessengerActivity;
import com.lovelz.lzlearncenter.ipc.provider.ContentProviderActivity;
import com.lovelz.lzlearncenter.ipc.socket.SocketClientActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * IPC机制
 *
 * @author lovelz
 * @date on 2018/10/12.
 */
public class IPCSimulateActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_simulate);

        Intent intent = new Intent(this, IPCSimulateService.class);
        startService(intent);

        findViewById(R.id.enter_messenger_communication).setOnClickListener(this);
        findViewById(R.id.enter_aidl).setOnClickListener(this);
        findViewById(R.id.enter_provider).setOnClickListener(this);
        findViewById(R.id.enter_socket).setOnClickListener(this);
        findViewById(R.id.enter_handler).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_messenger_communication:
                startActivity(new Intent(this, MessengerActivity.class));
                break;
            case R.id.enter_aidl:
                startActivity(new Intent(this, AIDLActivity.class));
                break;
            case R.id.enter_provider:
                startActivity(new Intent(this, ContentProviderActivity.class));
                break;
            case R.id.enter_socket:
                startActivity(new Intent(this, SocketClientActivity.class));
                break;
            case R.id.enter_handler:
                startActivity(new Intent(this, HandlerThreadActivity.class));
                break;
        }
    }
}
