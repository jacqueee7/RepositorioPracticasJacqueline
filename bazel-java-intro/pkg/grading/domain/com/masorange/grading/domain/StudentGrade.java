package com.masorange.grading.domain;

import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import java.util.Objects;

public final class StudentGrade {

    private final StudentId studentId;
    private final Subject subject;
    private final Grade grade;
    private final TeacherId teacherId;

    private StudentGrade(StudentId studentId, Subject subject, Grade grade, TeacherId teacherId) {
        Objects.requireNonNull(studentId, "StudentId must not be null");
        Objects.requireNonNull(subject, "Subject must not be null");
        Objects.requireNonNull(grade, "Grade must not be null");
        Objects.requireNonNull(teacherId, "TeacherId must not be null");
        this.studentId = studentId;
        this.subject = subject;
        this.grade = grade;
        this.teacherId = teacherId;
    }

    public static StudentGrade create(StudentId studentId, Subject subject, Grade grade, TeacherId teacherId) {
        return new StudentGrade(studentId, subject, grade, teacherId);
    }

    public StudentId studentId() {
        return studentId;
    }

    public Subject subject() {
        return subject;
    }

    public Grade grade() {
        return grade;
    }

    public TeacherId teacherId() {
        return teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGrade)) return false;
        StudentGrade that = (StudentGrade) o;
        return studentId.equals(that.studentId) && subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subject);
    }

    @Override
    public String toString() {
        return "StudentGrade{studentId=" + studentId + ", subject=" + subject
                + ", grade=" + grade + ", teacherId=" + teacherId + "}";
    }
}
