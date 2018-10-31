package com.lovelz.lzlearncenter.ipc.provider;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lovelz
 * @date on 2018/10/13.
 */
public class Game implements Parcelable {

    private String gameName;
    private String gameDescribe;

    public Game(String gameName, String gameDescribe) {
        this.gameName = gameName;
        this.gameDescribe = gameDescribe;
    }

    protected Game(Parcel in) {
        gameName = in.readString();
        gameDescribe = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gameName);
        dest.writeString(gameDescribe);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescribe() {
        return gameDescribe;
    }

    public void setGameDescribe(String gameDescribe) {
        this.gameDescribe = gameDescribe;
    }
}
