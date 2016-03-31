package model;

import java.util.ArrayList;

/**
 * Created by Carl on 22-3-2016.
 */
public class Rooster {
    private String mijnRooster;
    private ArrayList<Les> deLessen;

    public Rooster(String mijnRooster) {
        this.mijnRooster = mijnRooster;
        deLessen = new ArrayList<Les>();

    }

    public void setLes(Les les) {
        deLessen.add(les);
    }

    public ArrayList<Les> getLessen() {
        return deLessen;
    }

}
