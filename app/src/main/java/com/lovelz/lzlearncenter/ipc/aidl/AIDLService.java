package com.lovelz.lzlearncenter.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lovelz.lzlearncenter.Game;
import com.lovelz.lzlearncenter.IGameManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lovelz
 * @date on 2018/10/13.
 */
public class AIDLService extends Service {

    private CopyOnWriteArrayList<Game> gameList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IGameManager.Stub() {
        @Override
        public List<Game> getGameList() throws RemoteException {
            return gameList;
        }

        @Override
        public void addGame(Game game) throws RemoteException {
            gameList.add(game);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        gameList.add(new Game("LOL", "一起来玩吧！"));
        gameList.add(new Game("DNF", "一起来玩吧！"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
