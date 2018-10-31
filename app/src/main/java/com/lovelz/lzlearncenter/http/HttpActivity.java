package com.lovelz.lzlearncenter.http;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.http.connect.HttpURLConnectionActivity;
import com.lovelz.lzlearncenter.http.okhttp.OkHttpActivity;
import com.lovelz.lzlearncenter.http.volley.VolleyActivity;

/**
 * @author lovelz
 * @date on 2018/10/19.
 */
public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        findViewById(R.id.enter_http_url_connection).setOnClickListener(this);
        findViewById(R.id.enter_volley).setOnClickListener(this);
        findViewById(R.id.enter_okhttp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_http_url_connection:
                startActivity(new Intent(this, HttpURLConnectionActivity.class));
                break;
            case R.id.enter_volley:
                startActivity(new Intent(this, VolleyActivity.class));
                break;
            case R.id.enter_okhttp:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
        }
    }
}
