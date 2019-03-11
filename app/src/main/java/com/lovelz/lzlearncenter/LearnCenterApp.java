package com.lovelz.lzlearncenter;

import android.app.Application;

import com.taobao.sophix.SophixManager;

/**
 * @author lovelz
 * @date on 2018/11/2.
 */
public class LearnCenterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SophixManager.getInstance().queryAndLoadNewPatch();

    }
}
