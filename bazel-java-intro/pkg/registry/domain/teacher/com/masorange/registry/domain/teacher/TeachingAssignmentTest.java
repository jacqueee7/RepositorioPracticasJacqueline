package com.masorange.registry.domain.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TeachingAssignmentTest {

    @Test
    public void shouldCreateWithTeacherIdAndSubject() {
        TeacherId teacherId = TeacherId.generate();
        SubjectName subject = SubjectName.of("Mathematics");
        TeachingAssignment assignment = TeachingAssignment.create(teacherId, subject);
        assertEquals(teacherId, assignment.teacherId());
        assertEquals(subject, assignment.subjectName());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullTeacherId() {
        TeachingAssignment.create(null, SubjectName.of("Mathematics"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullSubjectName() {
        TeachingAssignment.create(TeacherId.generate(), null);
    }

    @Test
    public void shouldBeEqualByTeacherIdAndSubject() {
        TeacherId teacherId = TeacherId.generate();
        SubjectName subject = SubjectName.of("Mathematics");
        TeachingAssignment a1 = TeachingAssignment.create(teacherId, subject);
        TeachingAssignment a2 = TeachingAssignment.create(teacherId, subject);
        assertEquals(a1, a2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentSubject() {
        TeacherId teacherId = TeacherId.generate();
        TeachingAssignment a1 = TeachingAssignment.create(teacherId, SubjectName.of("Mathematics"));
        TeachingAssignment a2 = TeachingAssignment.create(teacherId, SubjectName.of("Physics"));
        assertNotEquals(a1, a2);
    }
}
