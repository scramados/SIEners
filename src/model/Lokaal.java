package model;

import java.io.Serializable;
import java.util.Objects;

public class Lokaal implements Serializable{
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

}
