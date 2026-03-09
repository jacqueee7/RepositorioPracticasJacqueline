package com.masorange.grading.application;

import static org.junit.Assert.assertEquals;

import com.masorange.grading.domain.Grade;
import com.masorange.grading.domain.StudentGrade;
import com.masorange.grading.domain.Subject;
import com.masorange.grading.infrastructure.InMemoryGradeRepository;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import org.junit.Test;

public class CalculateAverageTest {

    @Test
    public void shouldCalculateAverageOfMultipleGrades() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        CalculateAverage calculateAverage = CalculateAverage.create(repository);
        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate();

        repository.save(StudentGrade.create(studentId, Subject.of("Mathematics"), Grade.of(8.0), teacherId));
        repository.save(StudentGrade.create(studentId, Subject.of("Physics"), Grade.of(6.0), teacherId));
        repository.save(StudentGrade.create(studentId, Subject.of("Literature"), Grade.of(10.0), teacherId));

        double average = calculateAverage.execute(studentId);

        assertEquals(8.0, average, 0.001);
    }

    @Test
    public void shouldCalculateAverageOfSingleGrade() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        CalculateAverage calculateAverage = CalculateAverage.create(repository);
        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate();

        repository.save(StudentGrade.create(studentId, Subject.of("Mathematics"), Grade.of(7.5), teacherId));

        double average = calculateAverage.execute(studentId);

        assertEquals(7.5, average, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenNoGradesExist() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        CalculateAverage calculateAverage = CalculateAverage.create(repository);

        calculateAverage.execute(StudentId.generate());
    }
}
