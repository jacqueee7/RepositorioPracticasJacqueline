package com.masorange.registry.domain.student;

import java.util.Objects;

public final class Student {

    private final StudentId id;
    private final StudentName name;

    private Student(StudentId id, StudentName name) {
        Objects.requireNonNull(id, "Student id must not be null");
        Objects.requireNonNull(name, "Student name must not be null");
        this.id = id;
        this.name = name;
    }

    public static Student create(StudentId id, StudentName name) {
        return new Student(id, name);
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name + "}";
    }
}
