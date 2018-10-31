package com.lovelz.lzlearncenter;

import android.app.Application;
import android.util.Log;

/**
 * @author lovelz
 * @date on 2018/10/12.
 */
public class MyApplication extends Application {

    private static final String TAG = "IPC";

    @Override
    public void onCreate() {
        super.onCreate();

        //开启多线程会使Application运行多次，
        //解决的方法就是得到每个进程的名称，如果进程的名称和我们应用的进程名称相同则做我们应用的操作，如果不是则做其他进程的操作
        int pid = android.os.Process.myPid();
        Log.d(TAG, "current pid is " + pid);
    }
}
