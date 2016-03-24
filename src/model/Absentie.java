package model;

import java.util.Objects;

/**
 * Created by jason on 22-3-2016.
 */
public class Absentie {
    private Les les;
    private Student student;
    private boolean aanwezig;


    public Absentie(Les les, Student student, boolean aanwezig) {
        this.les = les;
        this.student = student;
        this.aanwezig = aanwezig;
    }

    public boolean setAfwezig(){
        return aanwezig = false;
    }


    public Student getStudent() {
        return student;
    }

    public boolean isAanwezig() {
        return aanwezig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Absentie)) return false;
        Absentie absentie = (Absentie) o;
        return aanwezig == absentie.aanwezig &&
                Objects.equals(les, absentie.les) &&
                Objects.equals(student, absentie.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(les, student, aanwezig);
    }
}
