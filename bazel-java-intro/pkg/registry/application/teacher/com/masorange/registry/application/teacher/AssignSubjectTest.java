package com.masorange.registry.application.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.domain.teacher.TeachingAssignment;
import com.masorange.registry.infrastructure.teacher.InMemoryTeachingAssignmentRepository;
import org.junit.Test;

public class AssignSubjectTest {

    @Test
    public void shouldAssignAndPersistSubject() {
        InMemoryTeachingAssignmentRepository repository = new InMemoryTeachingAssignmentRepository();
        AssignSubject assignSubject = AssignSubject.create(repository);
        TeacherId teacherId = TeacherId.generate();
        TeachingAssignment result = assignSubject.execute(teacherId, "Mathematics");
        assertEquals(teacherId, result.teacherId());
        assertEquals("Mathematics", result.subjectName().value());
        assertFalse(repository.findByTeacherId(teacherId).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptySubjectName() {
        InMemoryTeachingAssignmentRepository repository = new InMemoryTeachingAssignmentRepository();
        AssignSubject assignSubject = AssignSubject.create(repository);
        assignSubject.execute(TeacherId.generate(), "");
    }
}
