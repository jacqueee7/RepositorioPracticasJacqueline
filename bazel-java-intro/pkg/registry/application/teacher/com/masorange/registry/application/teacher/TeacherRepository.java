package com.masorange.registry.application.teacher;

import com.masorange.registry.domain.teacher.Teacher;
import com.masorange.registry.domain.teacher.TeacherId;
import java.util.Optional;

public interface TeacherRepository {

    void save(Teacher teacher);

    Optional<Teacher> findById(TeacherId id);
}
