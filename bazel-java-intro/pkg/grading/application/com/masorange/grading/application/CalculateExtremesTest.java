package com.masorange.grading.application;

import static org.junit.Assert.assertEquals;

import com.masorange.grading.domain.Grade;
import com.masorange.grading.domain.StudentGrade;
import com.masorange.grading.domain.Subject;
import com.masorange.grading.infrastructure.InMemoryGradeRepository;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId; // Importante
import org.junit.Test;

public class CalculateExtremesTest {

    @Test
    public void shouldCalculateMinAndMaxGrades() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate(); // Generamos un profesor para que no de error

        // Pasamos los 4 parámetros: alumno, asignatura, nota y profesor
        repository.save(StudentGrade.create(studentId, Subject.of("Math"), Grade.of(5.0), teacherId));
        repository.save(StudentGrade.create(studentId, Subject.of("Science"), Grade.of(9.0), teacherId));
        repository.save(StudentGrade.create(studentId, Subject.of("History"), Grade.of(7.5), teacherId));

        CalculateExtremes useCase = CalculateExtremes.create(repository);
        CalculateExtremes.GradeExtremes extremes = useCase.execute(studentId);

        assertEquals(5.0, extremes.min().value(), 0.001);
        assertEquals(9.0, extremes.max().value(), 0.001);
    }
}