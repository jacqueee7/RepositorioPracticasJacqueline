package com.masorange.grading.application;

import com.masorange.grading.domain.StudentGrade;
import com.masorange.registry.domain.student.StudentId;
import java.util.List;

public final class CalculateAverage {

    private final GradeRepository repository;

    private CalculateAverage(GradeRepository repository) {
        this.repository = repository;
    }

    public static CalculateAverage create(GradeRepository repository) {
        return new CalculateAverage(repository);
    }

    public double execute(StudentId studentId) {
        List<StudentGrade> grades = repository.findByStudentId(studentId);
        if (grades.isEmpty()) {
            throw new IllegalStateException("No grades found for student: " + studentId);
        }
        double sum = 0.0;
        for (StudentGrade sg : grades) {
            sum += sg.grade().value();
        }
        return sum / grades.size();
    }
}
