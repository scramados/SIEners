package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Carl on 22-3-2016.
 */
public class Rooster implements Serializable {
    private String mijnRooster;
    private ArrayList<Les> deLessen;

    public Rooster(String mijnRooster) {
        this.mijnRooster = mijnRooster;
        deLessen = new ArrayList<Les>();

    }
}
