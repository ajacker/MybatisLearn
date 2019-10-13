package com.ajacker.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ajacker
 */
public class Teacher {
    private int id;
    private String name;
    private List<Student> students;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students={\n" +
                students.stream().map(Student::toString).collect(Collectors.joining("\n"))
                + "\n}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
