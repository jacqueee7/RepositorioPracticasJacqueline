package com.masorange.registry.application.teacher;

import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.domain.teacher.TeachingAssignment;
import java.util.List;

public interface TeachingAssignmentRepository {

    void save(TeachingAssignment assignment);

    List<TeachingAssignment> findByTeacherId(TeacherId teacherId);
}
