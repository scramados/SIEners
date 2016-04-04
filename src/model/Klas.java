package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Klas implements Serializable {
    private String klasCode;
    private ArrayList<Student> studentenKlas;

    public Klas(String klasCode) {
        this.klasCode = klasCode;
        studentenKlas = new ArrayList<>();
    }

    public void addStudentKlas(Student student) {
        if (!studentenKlas.contains(student)) {
            studentenKlas.add(student);
        }
    }

    public ArrayList<Student> getStudentenKlas() {
        return studentenKlas;
    }

    public String getKlasCode() {
        return klasCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klas)) return false;
        Klas klas = (Klas) o;
        return Objects.equals(klasCode, klas.klasCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klasCode);
    }

}
