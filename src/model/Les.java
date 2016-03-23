package model;

import java.sql.Time;
import java.util.Date;

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


    public Les(Vak vak, Klas klas, Docent docent, String date, String startTijd, String eindTijd, Lokaal lokaal) {
        this.vak = vak;
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
        this.lokaal = lokaal;
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
