package model;

import java.util.Objects;

/**
 * Created by jason on 22-3-2016.
 */
public class Absentie {
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
    public int hashCode() {
        return Objects.hash(les, student);
    }
}
