package com.lovelz.lzlearncenter.ipc.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lovelz.lzlearncenter.R;

/**
 * @author lovelz
 * @date on 2018/10/15.
 */
public class ContentProviderActivity extends AppCompatActivity {

    private static final String TAG = ContentProviderActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);


        Uri gameUri = Uri.parse("content://com.lovelz.lzlearncenter.ipc.provider.GameProvider");
        ContentValues gameValues = new ContentValues();
        gameValues.put("_id", 2);
        gameValues.put("name", "DNF");
        gameValues.put("describe", "啦啦啦啦啦");
        //插入一条数据
        getContentResolver().insert(gameUri, gameValues);

        Cursor gameCursor = getContentResolver().query(gameUri, new String[]{"name", "describe"}, null, null, null);
        while (gameCursor.moveToNext()) {
            Game game = new Game(gameCursor.getString(0), gameCursor.getString(1));
            Log.d(TAG, game.getGameName() + "  -----  " + game.getGameDescribe());
        }
    }
}
