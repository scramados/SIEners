package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Student implements Serializable {
    private String studentNummer;
    private String voorNaam, tussenVoegsel, achterNaam, wachtwoord;
    private Klas mijnKlas;
    private Rooster mijnRooster;
    private ArrayList<Absentie> mijnabsenties;

    public Student(String studentNummer, String voorNaam, String achterNaam, String tussenVoegsel) {
        this.studentNummer = studentNummer;
        this.voorNaam = voorNaam;
        this.tussenVoegsel = tussenVoegsel;
        this.achterNaam = achterNaam;
        wachtwoord = "test";
        mijnabsenties = new ArrayList<>();
    }

    public Student(String studentNummer, String voorNaam, String achterNaam) {
        this.studentNummer = studentNummer;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        wachtwoord = "test";
        mijnabsenties = new ArrayList<>();
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersNaam() {
        return studentNummer;
    }

    public void addabsentie(Absentie absentie) {
        mijnabsenties.add(absentie);
    }

    public void removeabsentie(Absentie absentie) {
        mijnabsenties.remove(absentie);
    }

    public ArrayList<Absentie> getAbsentie() {
        return mijnabsenties;
    }

    public Rooster getRooster() {
        return mijnRooster;
    }

    public void setRooster(Rooster mijnRooster) {
        this.mijnRooster = mijnRooster;
    }

    public boolean controleerWachtwoord(String wachtwoord) {
        return wachtwoord.equals(this.wachtwoord);
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public String getTussenVoegsel() {
        if (tussenVoegsel != null) {
            return tussenVoegsel;
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(studentNummer, student.studentNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNummer);
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    @Override
    public String toString() {
        return achterNaam;
    }
}
