package model;

import java.io.Serializable;
import java.util.Objects;

public class Docent implements Serializable {
    private String voornaam, tussenVoegsel, achternaam, wachtwoord;


    public Docent(String completeNaam) {
        String temp = "";
        int counter = 0;
        for (int i = 0; i < completeNaam.length(); i++) {
            if (completeNaam.charAt(i) == ' ') {
                counter++;
            }
        }
        String[] slice = completeNaam.split(" ");
        if (counter == 2) {
            voornaam = slice[0];
            tussenVoegsel = slice[1];
            achternaam = slice[2];
        } else if (counter == 1) {
            voornaam = slice[0];
            tussenVoegsel = "";
            achternaam = slice[1];
        }
        this.wachtwoord = "test";
    }

    public String getGebruikersNaam() {
        return achternaam;
    }

    public boolean controleerWachtwoord(String wachtwoord) {
        return wachtwoord.equals(this.wachtwoord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Docent)) return false;
        Docent docent = (Docent) o;
        return Objects.equals(voornaam, docent.voornaam) &&
                Objects.equals(tussenVoegsel, docent.tussenVoegsel) &&
                Objects.equals(achternaam, docent.achternaam) &&
                Objects.equals(wachtwoord, docent.wachtwoord) &&
                Objects.equals(wachtwoord, docent.wachtwoord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voornaam, tussenVoegsel, achternaam, wachtwoord, wachtwoord);
    }

}
