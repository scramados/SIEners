package model;

import java.util.Objects;

public class Klas {
    private String klasCode;

    public Klas(String klasCode) {
        this.klasCode = klasCode;
    }


    public String getKlasCode() {
        return klasCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klas)) return false;
        Klas klas = (Klas) o;
        return Objects.equals(klasCode, klas.klasCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klasCode);
    }

    @Override
    public String toString() {
        return klasCode + "\n";
    }

}
