package model;

/**
 * Created by jason on 22-3-2016.
 */
public class Lokaal {
    //    private String vleugel;
    //    private String verdieping;
    //    private String lokaalNr;
    private String lokaalNaam;

    public Lokaal(String lokaalNaam) {
        this.lokaalNaam = lokaalNaam;
    }

    public String getLokaalNaam() {
        return lokaalNaam;
    }
}
