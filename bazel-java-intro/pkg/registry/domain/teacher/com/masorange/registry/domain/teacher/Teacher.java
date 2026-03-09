package com.masorange.registry.domain.teacher;

import java.util.Objects;

public final class Teacher {

    private final TeacherId id;
    private final TeacherName name;

    private Teacher(TeacherId id, TeacherName name) {
        Objects.requireNonNull(id, "Teacher id must not be null");
        Objects.requireNonNull(name, "Teacher name must not be null");
        this.id = id;
        this.name = name;
    }

    public static Teacher create(TeacherId id, TeacherName name) {
        return new Teacher(id, name);
    }

    public TeacherId id() {
        return id;
    }

    public TeacherName name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher that = (Teacher) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name=" + name + "}";
    }
}
