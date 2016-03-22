package model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by jason on 22-3-2016.
 */
public class Les {
    private Date date;
    private Time startTijd;
    private Time eindTijd;
    private Vak vak;
    private Docent docent;
    private Lokaal lokaal;
    private Klas klas;


    private boolean absent;

    public Les(Vak vak, Klas klas, Docent docent, Date date, Time time, boolean absent) {
        this.vak = vak;
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.time = time;
        this.absent = absent;
    }


    @Override
    public String toString() {
        return "Les{" +
                "vak=" + vak +
                ", klas=" + klas +
                ", docent=" + docent +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
