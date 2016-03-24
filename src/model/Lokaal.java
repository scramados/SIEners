package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lokaal)) return false;
        Lokaal lokaal = (Lokaal) o;
        return Objects.equals(lokaalNaam, lokaal.lokaalNaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lokaalNaam);
    }

    @Override
    public String toString() {
        return lokaalNaam;
    }
}
