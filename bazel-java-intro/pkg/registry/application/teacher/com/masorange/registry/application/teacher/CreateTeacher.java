package com.masorange.registry.application.teacher;

import com.masorange.registry.domain.teacher.Teacher;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.domain.teacher.TeacherName;

public final class CreateTeacher {

    private final TeacherRepository repository;

    private CreateTeacher(TeacherRepository repository) {
        this.repository = repository;
    }

    public static CreateTeacher create(TeacherRepository repository) {
        return new CreateTeacher(repository);
    }

    public Teacher execute(String name) {
        TeacherId id = TeacherId.generate();
        TeacherName teacherName = TeacherName.of(name);
        Teacher teacher = Teacher.create(id, teacherName);
        repository.save(teacher);
        return teacher;
    }
}
