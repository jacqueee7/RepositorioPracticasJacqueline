package com.masorange.grading.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import org.junit.Test;

public class StudentGradeTest {

    @Test
    public void shouldCreateWithAllFields() {
        StudentId studentId = StudentId.generate();
        Subject subject = Subject.of("Mathematics");
        Grade grade = Grade.of(8.5);
        TeacherId teacherId = TeacherId.generate();

        StudentGrade studentGrade = StudentGrade.create(studentId, subject, grade, teacherId);

        assertEquals(studentId, studentGrade.studentId());
        assertEquals(subject, studentGrade.subject());
        assertEquals(grade, studentGrade.grade());
        assertEquals(teacherId, studentGrade.teacherId());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullStudentId() {
        StudentGrade.create(null, Subject.of("Math"), Grade.of(5.0), TeacherId.generate());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullSubject() {
        StudentGrade.create(StudentId.generate(), null, Grade.of(5.0), TeacherId.generate());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullGrade() {
        StudentGrade.create(StudentId.generate(), Subject.of("Math"), null, TeacherId.generate());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullTeacherId() {
        StudentGrade.create(StudentId.generate(), Subject.of("Math"), Grade.of(5.0), null);
    }

    @Test
    public void shouldBeEqualByStudentIdAndSubject() {
        StudentId studentId = StudentId.generate();
        Subject subject = Subject.of("Mathematics");
        TeacherId teacherId = TeacherId.generate();

        StudentGrade sg1 = StudentGrade.create(studentId, subject, Grade.of(7.0), teacherId);
        StudentGrade sg2 = StudentGrade.create(studentId, subject, Grade.of(9.0), teacherId);

        assertEquals(sg1, sg2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentSubject() {
        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate();

        StudentGrade sg1 = StudentGrade.create(studentId, Subject.of("Math"), Grade.of(7.0), teacherId);
        StudentGrade sg2 = StudentGrade.create(studentId, Subject.of("Physics"), Grade.of(7.0), teacherId);

        assertNotEquals(sg1, sg2);
    }
}
