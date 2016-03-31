package model;

import java.util.ArrayList;
import java.util.Objects;

public class Docent {
    private String gebruikersNaam;
    private String wachtwoord;
    private Rooster mijnRooster;


    public Docent(String gebruikersNaam, String wachtwoord) {
        // checkt of de docentnaam 1 of 2 spaties heeft en voegt deze dan aan elkaar
        String temp = "";
        int counter = 0;
        for (int i = 0; i < gebruikersNaam.length() + 1; i++) {
            if (gebruikersNaam.charAt(i) == ' ') {
                counter++;
            }
        }
        String[] slice = gebruikersNaam.split(" ");
        if (counter == 2) {
            temp = slice[1] + slice[2];
        } else if (counter == 1) {
            temp = slice[1];

            this.gebruikersNaam = temp;
            this.wachtwoord = "test";
        }
    }

    public void fixGebruikersnaam() {
        // checkt of de docentnaam 1 of 2 spaties heeft en voegt deze dan aan elkaar
        String temp = "";
        int counter = 0;
        for (int i = 0; i < gebruikersNaam.length() + 1; i++) {
            if (gebruikersNaam.charAt(i) == ' ') {
                counter++;
            }
        }
        String[] slice = gebruikersNaam.split(" ");
        if (counter == 2) {
            temp = slice[1] + slice[2];
        } else if (counter == 1) {
            temp = slice[1];
        }
        gebruikersNaam = temp;
    }

    public Docent(String gebruikersNaam) {
        this.gebruikersNaam = gebruikersNaam;
        wachtwoord = "test";
    }

    public Rooster getRooster() {
        return mijnRooster;
    }

    public void setRooster(Rooster mijnRooster) {
        this.mijnRooster = mijnRooster;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    public boolean controleerWachtwoord(String wachtwoord) {
        return wachtwoord.equals(this.wachtwoord);
    }

//    public void voegVakToe(Vak nieuwvak) {
//        mijnVakken.add(nieuwvak);
//    }

//    public ArrayList<Vak> getVakken() {
//        return mijnVakken;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Docent)) return false;
        Docent docent = (Docent) o;
        return Objects.equals(gebruikersNaam, docent.gebruikersNaam) &&
                Objects.equals(wachtwoord, docent.wachtwoord) &&
                /*Objects.equals(mijnVakken, docent.mijnVakken) &&*/
                Objects.equals(mijnRooster, docent.mijnRooster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gebruikersNaam, wachtwoord, mijnRooster);
    }

    @Override
    public String toString() {
        return gebruikersNaam;
    }
}
