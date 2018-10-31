package com.lovelz.lzlearncenter.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lovelz
 * @date on 2018/10/15.
 */
public class SocketServerService extends Service {

    public static final String TAG = SocketServerService.class.getSimpleName();

    private boolean isServiceDestroy;

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new TcpServer()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer extends Thread {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8886);
            } catch (IOException e) {
                return;
            }

            while (!isServiceDestroy) {
                try {
                    final Socket client = serverSocket.accept();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        //用于接收客户端发来的消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("Hello, 我是服务端！");
        while (!isServiceDestroy) {
            String clientMessage = in.readLine();
            Log.i(TAG, "收到客户端发来的信息" + clientMessage);
            if (TextUtils.isEmpty(clientMessage)) {
                Log.i(TAG, "客户端断开了连接");
                break;
            }
            out.println("收到客户端发来的信息为：" + clientMessage);
        }
        out.close();
        in.close();
        client.close();
    }

    @Override
    public void onDestroy() {
        isServiceDestroy = true;
        super.onDestroy();
    }
}
