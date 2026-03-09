package com.masorange.registry.infrastructure.teacher;

import com.masorange.registry.application.teacher.TeacherRepository;
import com.masorange.registry.domain.teacher.Teacher;
import com.masorange.registry.domain.teacher.TeacherId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryTeacherRepository implements TeacherRepository {

    private final Map<TeacherId, Teacher> storage = new HashMap<>();

    @Override
    public void save(Teacher teacher) {
        storage.put(teacher.id(), teacher);
    }

    @Override
    public Optional<Teacher> findById(TeacherId id) {
        return Optional.ofNullable(storage.get(id));
    }
}
