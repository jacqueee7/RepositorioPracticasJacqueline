package com.masorange.grading.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.masorange.grading.domain.StudentGrade;
import com.masorange.grading.infrastructure.InMemoryGradeRepository;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import org.junit.Test;

public class RecordGradeTest {

    @Test
    public void shouldRecordAndPersistGrade() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        RecordGrade recordGrade = RecordGrade.create(repository);
        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate();

        StudentGrade result = recordGrade.execute(studentId, "Mathematics", 8.5, teacherId);

        assertEquals(studentId, result.studentId());
        assertEquals("Mathematics", result.subject().value());
        assertEquals(8.5, result.grade().value(), 0.001);
        assertEquals(teacherId, result.teacherId());
        assertFalse(repository.findByStudentId(studentId).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithInvalidGrade() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        RecordGrade recordGrade = RecordGrade.create(repository);

        recordGrade.execute(StudentId.generate(), "Mathematics", 11.0, TeacherId.generate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptySubject() {
        InMemoryGradeRepository repository = new InMemoryGradeRepository();
        RecordGrade recordGrade = RecordGrade.create(repository);

        recordGrade.execute(StudentId.generate(), "", 8.0, TeacherId.generate());
    }
}
