package com.lovelz.lzlearncenter.annotate;

import android.app.Activity;
import android.os.Bundle;


import com.lovelz.lzlearncenter.R;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
public class GameFlavourActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_layout);

        GameFlavourManager gameFlavourManager = new GameFlavourManager();
        gameFlavourManager.setFlavour(GameFlavourManager.LOL);
    }
}
