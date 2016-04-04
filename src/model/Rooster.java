package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rooster implements Serializable {
    private String mijnRooster;
    private ArrayList<Les> deLessen;

    public Rooster(String mijnRooster) {
        this.mijnRooster = mijnRooster;
        deLessen = new ArrayList<Les>();

    }
}
