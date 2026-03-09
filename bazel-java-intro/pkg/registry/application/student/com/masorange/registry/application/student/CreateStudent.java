package com.masorange.registry.application.student;

import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.student.StudentName;

public final class CreateStudent {

    private final StudentRepository repository;

    private CreateStudent(StudentRepository repository) {
        this.repository = repository;
    }

    public static CreateStudent create(StudentRepository repository) {
        return new CreateStudent(repository);
    }

    public Student execute(String name) {
        StudentId id = StudentId.generate();
        StudentName studentName = StudentName.of(name);
        Student student = Student.create(id, studentName);
        repository.save(student);
        return student;
    }
}
