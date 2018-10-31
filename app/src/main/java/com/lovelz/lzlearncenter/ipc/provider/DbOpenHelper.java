package com.lovelz.lzlearncenter.ipc.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author lovelz
 * @date on 2018/10/15.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "provider.db";
    public static final String TABLE_NAME = "game";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + "(_id integer primary key, name TEXT, describe TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
