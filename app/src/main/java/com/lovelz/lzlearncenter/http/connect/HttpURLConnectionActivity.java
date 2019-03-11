package com.lovelz.lzlearncenter.http.connect;

import android.os.Bundle;
import android.util.Log;

import com.lovelz.lzlearncenter.R;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2018/10/19.
 */
public class HttpURLConnectionActivity extends AppCompatActivity {

    private static final String TAG = HttpURLConnectionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);

        getMethod("https://www.baidu.com");
        postMethod("https://www.baidu.com");
        postMethodWithParam("https://www.baidu.com");
        postMethodWithJson("https://www.baidu.com");
    }

    /**
     * GET方法请求网络
     *
     *
        1， 使用connection.setRequestMethod(“GET”);设置请求方式。
        2， 使用connection.connect();连接网络。请求行，请求头的设置必须放在网络连接前。
        3， connection.getInputStream()只是得到一个流对象，并不是数据，不过我们可以从流中读出数据，从流中读取数据的操作必须放在子线程。
        4， connection.getInputStream()得到一个流对象，从这个流对象中只能读取一次数据，第二次读取时将会得到空数据。
     *
     * @param url 请求地址
     */
    private void getMethod(final String url) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection mConnection = null;
                try {
                    URL mUrl = new URL(url);
                    mConnection = (HttpURLConnection) mUrl.openConnection();
                    mConnection.setConnectTimeout(10000);
                    mConnection.setReadTimeout(10000);
                    mConnection.setRequestMethod("GET");
                    mConnection.connect();

                    if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = mConnection.getInputStream();
                        ByteArrayOutputStream result = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inputStream.read(buffer)) != -1) {
                            result.write(buffer, 0, length);
                        }
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                            String str = result.toString(StandardCharsets.UTF_8.name());
//                        }
                        Log.i(TAG, result.toString("UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * POST方法请求网络(不带参数)
     *
     * @param url 请求地址
     */
    private void postMethod(final String url) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection mConnection = null;
                try {
                    URL mUrl = new URL(url);
                    mConnection = (HttpURLConnection) mUrl.openConnection();
                    mConnection.setConnectTimeout(10000);
                    mConnection.setReadTimeout(10000);
                    mConnection.setRequestMethod("POST");
                    //允许写入
                    mConnection.setDoOutput(true);
                    //允许读取
                    mConnection.setDoInput(true);
                    //不使用缓存
                    mConnection.setUseCaches(false);
                    mConnection.connect();

                    if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = mConnection.getInputStream();
                        ByteArrayOutputStream result = input2String(inputStream);
                        Log.d(TAG, result.toString("UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * POST方法请求网络（带键值对参数）
        1，post方式传递参数的本质是：从连接中得到一个输出流，通过输出流把数据写到服务器。
        2，数据的拼接采用键值对格式，键与值之间用=连接。每个键值对之间用&连接。
     * @param url
     */
    private void postMethodWithParam(final String url) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection mConnection = null;
                try {
                    URL mUrl = new URL(url);
                    mConnection = (HttpURLConnection) mUrl.openConnection();
                    mConnection.setConnectTimeout(10000);
                    mConnection.setReadTimeout(10000);
                    mConnection.setRequestMethod("POST");
                    mConnection.setDoOutput(true);
                    mConnection.setDoInput(true);
                    mConnection.setUseCaches(false);
                    mConnection.connect();

                    //写入参数
                    String paramValue = "userName=lisi&password=123456";
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mConnection.getOutputStream(), "UTF-8"));
                    writer.write(paramValue);
                    writer.close();

                    if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = mConnection.getInputStream();
                        ByteArrayOutputStream result = input2String(inputStream);
                        Log.d(TAG, result.toString("UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * POST方法请求网络（带json参数）
        1，传递json格式数据时需要在请求头中设置参数类型是json格式。
        2，body是json格式的字符串。
     * @param url
     */
    private void postMethodWithJson(final String url) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection mConnection = null;
                try {
                    URL mUrl = new URL(url);
                    mConnection = (HttpURLConnection) mUrl.openConnection();
                    mConnection.setConnectTimeout(10000);
                    mConnection.setReadTimeout(10000);
                    mConnection.setRequestMethod("POST");
                    mConnection.setDoOutput(true);
                    mConnection.setDoInput(true);
                    //设置参数类型为json
                    mConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    mConnection.connect();

                    String bodyJson = "{userName:lisi,password:123456}";
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mConnection.getOutputStream(), "UTF-8"));
                    writer.write(bodyJson);
                    writer.close();

                    if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = mConnection.getInputStream();
                        ByteArrayOutputStream result = input2String(inputStream);
                        Log.i(TAG, result.toString("UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private HttpURLConnection getHttpURLConnection(String url) {
        HttpURLConnection mHttpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(10000);
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setRequestMethod("POST");
            //添加请求头
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            //接受输入流
            mHttpURLConnection.setDoInput(true);
            //传递参数时需开启
            mHttpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private ByteArrayOutputStream input2String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        if ((length = inputStream.read()) != -1) {
            result.write(buffer, 0, length);
        }
        return result;
    }
}
