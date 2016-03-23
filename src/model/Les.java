package model;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * Created by jason on 22-3-2016.
 */
public class Les {
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

    public Les(String date, String startTijd, String eindTijd) {
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
    }

    public String getDate() {
        return date;
    }

    public String getStartTijd() {
        return startTijd;
    }

    public String getEindTijd() {
        return eindTijd;
    }

    public Vak getVak() {
        return vak;
    }

    public Docent getDocent() {
        return docent;
    }

    public Lokaal getLokaal() {
        return lokaal;
    }

    public Klas getKlas() {
        return klas;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public void setLokaal(Lokaal lokaal) {
        this.lokaal = lokaal;
    }

    public void setDocent(Docent docent) {
        this.docent = docent;
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

    @Override
    public String toString() {
        return "Les{" +
                "vak=" + vak +
                ", klas=" + klas +
                ", docent=" + docent +
                ", date=" + date +
                ", time=" + startTijd + " - " + eindTijd +
                '}';
    }
}
