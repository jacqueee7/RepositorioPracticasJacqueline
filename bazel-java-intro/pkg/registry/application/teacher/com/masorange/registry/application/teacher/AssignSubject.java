package com.masorange.registry.application.teacher;

import com.masorange.registry.domain.teacher.SubjectName;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.domain.teacher.TeachingAssignment;

public final class AssignSubject {

    private final TeachingAssignmentRepository repository;

    private AssignSubject(TeachingAssignmentRepository repository) {
        this.repository = repository;
    }

    public static AssignSubject create(TeachingAssignmentRepository repository) {
        return new AssignSubject(repository);
    }

    public TeachingAssignment execute(TeacherId teacherId, String subjectName) {
        SubjectName subject = SubjectName.of(subjectName);
        TeachingAssignment assignment = TeachingAssignment.create(teacherId, subject);
        repository.save(assignment);
        return assignment;
    }
}
