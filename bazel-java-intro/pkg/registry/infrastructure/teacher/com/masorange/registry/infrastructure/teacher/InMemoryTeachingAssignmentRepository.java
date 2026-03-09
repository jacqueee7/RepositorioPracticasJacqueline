package com.masorange.registry.infrastructure.teacher;

import com.masorange.registry.application.teacher.TeachingAssignmentRepository;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.domain.teacher.TeachingAssignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InMemoryTeachingAssignmentRepository implements TeachingAssignmentRepository {

    private final List<TeachingAssignment> storage = new ArrayList<>();

    @Override
    public void save(TeachingAssignment assignment) {
        storage.add(assignment);
    }

    @Override
    public List<TeachingAssignment> findByTeacherId(TeacherId teacherId) {
        List<TeachingAssignment> result = new ArrayList<>();
        for (TeachingAssignment assignment : storage) {
            if (assignment.teacherId().equals(teacherId)) {
                result.add(assignment);
            }
        }
        return Collections.unmodifiableList(result);
    }
}
