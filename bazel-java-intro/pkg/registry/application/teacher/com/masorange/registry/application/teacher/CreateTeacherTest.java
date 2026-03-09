package com.masorange.registry.application.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.masorange.registry.domain.teacher.Teacher;
import com.masorange.registry.infrastructure.teacher.InMemoryTeacherRepository;
import org.junit.Test;

public class CreateTeacherTest {

    @Test
    public void shouldCreateAndPersistTeacher() {
        InMemoryTeacherRepository repository = new InMemoryTeacherRepository();
        CreateTeacher createTeacher = CreateTeacher.create(repository);
        Teacher teacher = createTeacher.execute("Carlos Lopez");
        assertEquals("Carlos Lopez", teacher.name().value());
        assertTrue(repository.findById(teacher.id()).isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptyName() {
        InMemoryTeacherRepository repository = new InMemoryTeacherRepository();
        CreateTeacher createTeacher = CreateTeacher.create(repository);
        createTeacher.execute("");
    }
}
