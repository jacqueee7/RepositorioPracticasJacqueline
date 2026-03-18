package com.masorange.grading.application;

import com.masorange.grading.domain.Grade;
import com.masorange.grading.domain.StudentGrade;
import com.masorange.registry.domain.student.StudentId;

import java.util.Comparator;
import java.util.List;

public final class CalculateExtremes {

    private final GradeRepository repository;

    private CalculateExtremes(GradeRepository repository) {
        this.repository = repository;
    }

    public static CalculateExtremes create(GradeRepository repository) {
        return new CalculateExtremes(repository);
    }

    // Cambiamos record por una clase estática para que sea compatible con Java 11
    public static class GradeExtremes {
        private final Grade min;
        private final Grade max;

        public GradeExtremes(Grade min, Grade max) {
            this.min = min;
            this.max = max;
        }

        public Grade min() { return min; }
        public Grade max() { return max; }
    }

    public GradeExtremes execute(StudentId studentId) {
        List<StudentGrade> grades = repository.findByStudentId(studentId);

        if (grades.isEmpty()) {
            throw new IllegalArgumentException("No grades found for student: " + studentId.value());
        }

        Grade min = grades.stream()
                .map(StudentGrade::grade)
                .min(Comparator.comparingDouble(Grade::value))
                .orElseThrow(() -> new RuntimeException("Error calculating min grade"));

        Grade max = grades.stream()
                .map(StudentGrade::grade)
                .max(Comparator.comparingDouble(Grade::value))
                .orElseThrow(() -> new RuntimeException("Error calculating max grade"));

        return new GradeExtremes(min, max);
    }
}