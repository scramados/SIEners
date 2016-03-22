package model;

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
}
