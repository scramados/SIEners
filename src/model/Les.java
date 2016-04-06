package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Les implements Serializable {
    private String date;
    private String startTijd;
    private String eindTijd;
    private Vak vak;
    private Docent docent;
    private Lokaal lokaal;
    private Klas klas;

    public Les(Klas klas, Docent docent, String date, String startTijd, String eindTijd) {
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
    }

    public Les(Vak vak, Lokaal lokaal, Klas klas, Docent docent, String date, String startTijd, String eindTijd) {
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
        this.lokaal = lokaal;
        this.vak = vak;
    }


    public String getDateString() {

        return (date);

    }

    public String getStartTijdString() {

        return (startTijd);


    }

    public String getEindTijdString() {

        return (eindTijd);
    }

    public Vak getVak() {
        return vak;
    }

    public Docent getDocent() {
        return docent;
    }

    public void setDocent(Docent docent) {
        this.docent = docent;
    }

    public Lokaal getLokaal() {
        return lokaal;
    }

    public Klas getKlas() {
        return klas;
    }

    public void setKlas(Klas klas) {
        this.klas = klas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Les)) return false;
        Les les = (Les) o;
        return Objects.equals(date, les.date) &&
                Objects.equals(startTijd, les.startTijd) &&
                Objects.equals(eindTijd, les.eindTijd) &&
                Objects.equals(vak, les.vak) &&
                Objects.equals(docent, les.docent) &&
                Objects.equals(lokaal, les.lokaal) &&
                Objects.equals(klas, les.klas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTijd, eindTijd, vak, docent, lokaal, klas);
    }
}
