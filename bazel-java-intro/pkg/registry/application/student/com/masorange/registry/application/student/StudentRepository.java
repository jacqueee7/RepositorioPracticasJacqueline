package com.masorange.registry.application.student;

import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.student.StudentId;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(StudentId id);
}
