package com.masorange.registry.domain.teacher;

import java.util.Objects;

public final class TeachingAssignment {

    private final TeacherId teacherId;
    private final SubjectName subjectName;

    private TeachingAssignment(TeacherId teacherId, SubjectName subjectName) {
        Objects.requireNonNull(teacherId, "TeacherId must not be null");
        Objects.requireNonNull(subjectName, "SubjectName must not be null");
        this.teacherId = teacherId;
        this.subjectName = subjectName;
    }

    public static TeachingAssignment create(TeacherId teacherId, SubjectName subjectName) {
        return new TeachingAssignment(teacherId, subjectName);
    }

    public TeacherId teacherId() {
        return teacherId;
    }

    public SubjectName subjectName() {
        return subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeachingAssignment)) return false;
        TeachingAssignment that = (TeachingAssignment) o;
        return teacherId.equals(that.teacherId) && subjectName.equals(that.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectName);
    }

    @Override
    public String toString() {
        return "TeachingAssignment{teacherId=" + teacherId + ", subjectName=" + subjectName + "}";
    }
}
