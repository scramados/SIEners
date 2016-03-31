package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Les {
    private Date date;
    private Date startTijd;
    private Date eindTijd;
    private Vak vak;
    private Docent docent;
    private Lokaal lokaal;
    private Klas klas;


    public Les(Klas klas, Docent docent, Date date, Date startTijd, Date eindTijd) {
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
    }
    public Les(Lokaal lokaal, Klas klas, Docent docent, Date date, Date startTijd, Date eindTijd) {
        this.klas = klas;
        this.docent = docent;
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
        this.lokaal = lokaal;
    }


    public Les(Date date, Date startTijd, Date eindTijd) {
        this.date = date;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
    }

    public String getDateString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);

    }

    public String getStartTijdString() {
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(startTijd);
    }

    public String getEindTijdString() {
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(eindTijd);
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

    @Override
    public String toString() {
        return "Les{" +
                "vak=" + vak +
                ", klas=" + klas +
                ", docent=" + docent +
                ", date=" + date +
                ", time=" + startTijd + " - " + eindTijd +
                '}' + "\n";
    }
}
