package com.lovelz.lzlearncenter.ipc.socket;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lovelz.lzlearncenter.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author lovelz
 * @date on 2018/10/15.
 */
public class SocketClientActivity extends AppCompatActivity implements View.OnClickListener {

    private PrintWriter mPrintWriter;
    private TextView messageInfo;
    private EditText sendInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        messageInfo = findViewById(R.id.message_info);
        sendInfo = findViewById(R.id.send_info);

        findViewById(R.id.send_sure).setOnClickListener(this);

        Intent socketIntent = new Intent(this, SocketServerService.class);
        startService(socketIntent);

        new Thread(){
            @Override
            public void run() {
                connectServer();
            }
        }.start();
    }

    /**
     * 连接至服务器
     */
    private void connectServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8886);
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            } catch (IOException e) {
                SystemClock.sleep(1000);
            }
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!isFinishing()) {
                final String msg = in.readLine();
                if (!TextUtils.isEmpty(msg)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            messageInfo.setText(messageInfo.getText() + "\n" + "服务端：" + msg);
                        }
                    });
                }
            }

            mPrintWriter.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_sure:
                //发送信息
                final String sendMessage = sendInfo.getText().toString().trim();
                if (!TextUtils.isEmpty(sendMessage) && mPrintWriter != null) {
                    new Thread(){
                        @Override
                        public void run() {
                            mPrintWriter.println(sendMessage);

                        }
                    }.start();
                    messageInfo.setText(messageInfo.getText() + "\n" + "客户端：" + sendMessage);
                    sendInfo.setText("");

                }
                break;
        }
    }
}
