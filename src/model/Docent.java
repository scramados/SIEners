package model;

import java.io.Serializable;
import java.util.Objects;

public class Docent implements Serializable {
    private String voornaam, tussenVoegsel, achternaam, wachtwoord;
    private Rooster mijnRooster;


    public Docent(String gebruikersNaam) {
        // checkt of de docentnaam 1 of 2 spaties heeft en voegt deze dan aan elkaar
        String temp = "";
        int counter = 0;
        for (int i = 0; i < gebruikersNaam.length(); i++) {
            if (gebruikersNaam.charAt(i) == ' ') {
                counter++;
            }
        }
        String[] slice = gebruikersNaam.split(" ");
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
                Objects.equals(wachtwoord, docent.wachtwoord) &&
                Objects.equals(mijnRooster, docent.mijnRooster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voornaam, tussenVoegsel, achternaam, wachtwoord, wachtwoord, mijnRooster);
    }

}
