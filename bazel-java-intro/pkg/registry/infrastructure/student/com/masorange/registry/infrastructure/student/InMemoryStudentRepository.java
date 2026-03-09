package com.masorange.registry.infrastructure.student;

import com.masorange.registry.application.student.StudentRepository;
import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.student.StudentId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryStudentRepository implements StudentRepository {

    private final Map<StudentId, Student> storage = new HashMap<>();

    @Override
    public void save(Student student) {
        storage.put(student.id(), student);
    }

    @Override
    public Optional<Student> findById(StudentId id) {
        return Optional.ofNullable(storage.get(id));
    }
}
