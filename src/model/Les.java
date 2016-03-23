package model;

/**
 * Created by Carl on 22-3-2016.
 */
public class Les {
    private String datum;
    private String tijd;
    private Klas deKlas;
    private Vak leVak;
    private Student deStudent;
    private Docent deDocent;

    public Les(String datum, String tijd){
        this.datum=datum;
        this.tijd=tijd;

    }
    public String getDatum(){
        return datum;
    }
    public String getTijd(){
        return tijd;
    }
}
