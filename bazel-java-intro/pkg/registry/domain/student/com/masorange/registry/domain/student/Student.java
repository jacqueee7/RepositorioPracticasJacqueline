package com.masorange.registry.domain.student;

import java.util.Objects;

public final class Student {

    private final StudentId id;
    private final StudentName name;
    private final Email email;

    private Student(StudentId id, StudentName name, Email email) {
        Objects.requireNonNull(id, "Student id must not be null");
        Objects.requireNonNull(name, "Student name must not be null");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Student create(StudentId id, StudentName name) {
        return new Student(id, name, null);
    }

    public static Student create(StudentId id, StudentName name, Email email) {
        return new Student(id, name, email);
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() {
        return name;
    }

    public Email email() {
        return email;
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
        return "Student{id=" + id + ", name=" + name + ", email=" + email + "}";
    }
}
