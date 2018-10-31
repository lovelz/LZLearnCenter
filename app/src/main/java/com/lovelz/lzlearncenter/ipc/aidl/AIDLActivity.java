package com.lovelz.lzlearncenter.ipc.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lovelz.lzlearncenter.Game;
import com.lovelz.lzlearncenter.IGameManager;
import com.lovelz.lzlearncenter.R;

/**
 * @author lovelz
 * @date on 2018/10/13.
 */
public class AIDLActivity extends AppCompatActivity {

    private static final String TAG = AIDLActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        Intent aidlIntent = new Intent(this, AIDLService.class);
        bindService(aidlIntent, mAidlConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mAidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IGameManager gameManager = IGameManager.Stub.asInterface(service);
            try {
                Game otherGame = new Game("Dota", "lalalalalalla");
                gameManager.addGame(otherGame);
                for (Game game : gameManager.getGameList()) {
                    Log.d(TAG, game.getGameName() + "  ---  " + game.getGameDescribe());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mAidlConnection);
    }
}
