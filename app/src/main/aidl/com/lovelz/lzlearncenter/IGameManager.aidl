// IGameManager.aidl
package com.lovelz.lzlearncenter;

import com.lovelz.lzlearncenter.Game;

// Declare any non-default types here with import statements

interface IGameManager {
    List<Game> getGameList();
    void addGame(in Game game);
}
