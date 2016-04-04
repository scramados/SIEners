package model;

import java.io.Serializable;
import java.util.Objects;

public class Absentie implements Serializable {
    private Les les;
    private Student student;

    public Absentie(Les les, Student student) {
        this.les = les;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Les getLes() {
        return les;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Absentie) {
            Absentie ab = (Absentie) o;
            if (this.les.equals(ab.getLes())) {
                result = true;
            }

        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(les, student);
    }
}
