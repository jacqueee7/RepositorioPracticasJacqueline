package com.masorange.grading.application;

import com.masorange.grading.domain.Grade;
import com.masorange.grading.domain.StudentGrade;
import com.masorange.grading.domain.Subject;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;

public final class RecordGrade {

    private final GradeRepository repository;

    private RecordGrade(GradeRepository repository) {
        this.repository = repository;
    }

    public static RecordGrade create(GradeRepository repository) {
        return new RecordGrade(repository);
    }

    public StudentGrade execute(StudentId studentId, String subjectName, double gradeValue, TeacherId teacherId) {
        Subject subject = Subject.of(subjectName);
        Grade grade = Grade.of(gradeValue);
        StudentGrade studentGrade = StudentGrade.create(studentId, subject, grade, teacherId);
        repository.save(studentGrade);
        return studentGrade;
    }
}
