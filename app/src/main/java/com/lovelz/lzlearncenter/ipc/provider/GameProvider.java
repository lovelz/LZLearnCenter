package com.lovelz.lzlearncenter.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author lovelz
 * @date on 2018/10/15.
 */
public class GameProvider extends ContentProvider {

    private static final String AUTHORITY = "com.lovelz.lzlearncenter.ipc.provider.GameProvider";
    private static final Uri GAME_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private Context mContext;
    private String tableName;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        tableName = DbOpenHelper.TABLE_NAME;
        initProvider();
        return false;
    }

    private void initProvider() {
        db = new DbOpenHelper(mContext).getWritableDatabase();
        new Thread(){
            @Override
            public void run() {
                db.execSQL("delete from " + tableName);
                db.execSQL("insert into " + tableName + " values(1, 'LOL', '比赛开始咯')");
            }
        }.start();
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return db.query(tableName, projection, selection, selectionArgs, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        db.insert(tableName, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
