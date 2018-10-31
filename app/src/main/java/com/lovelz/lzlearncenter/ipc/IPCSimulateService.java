package com.lovelz.lzlearncenter.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author lovelz
 * @date on 2018/10/12.
 */
public class IPCSimulateService extends Service {

    private static final String TAG = IPCSimulateService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "IPCSimulateService is onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "IPCSimulateActivity is onCreate");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Binder binder = new Binder();

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "IPCSimulateService is onDestroy");
    }
}
