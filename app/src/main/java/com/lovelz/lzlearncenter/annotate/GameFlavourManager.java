package com.lovelz.lzlearncenter.annotate;


import androidx.annotation.IntDef;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
public class GameFlavourManager {

    private int flavour;

    public static final int LOL = 0;

    public static final int DNF = 1;

    public static final int DOTA = 2;

    @IntDef({LOL, DNF, DOTA})
    public @interface Flavour {

    }

    @Flavour
    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(@Flavour int flavour) {
        this.flavour = flavour;
    }

}
