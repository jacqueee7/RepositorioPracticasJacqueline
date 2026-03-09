package com.masorange.registry.domain.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TeacherTest {

    @Test
    public void shouldCreateWithIdAndName() {
        TeacherId id = TeacherId.generate();
        TeacherName name = TeacherName.of("Carlos Lopez");
        Teacher teacher = Teacher.create(id, name);
        assertEquals(id, teacher.id());
        assertEquals(name, teacher.name());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullId() {
        Teacher.create(null, TeacherName.of("Carlos"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullName() {
        Teacher.create(TeacherId.generate(), null);
    }

    @Test
    public void shouldBeEqualById() {
        TeacherId id = TeacherId.generate();
        Teacher teacher1 = Teacher.create(id, TeacherName.of("Carlos"));
        Teacher teacher2 = Teacher.create(id, TeacherName.of("Otro Nombre"));
        assertEquals(teacher1, teacher2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentId() {
        Teacher teacher1 = Teacher.create(TeacherId.generate(), TeacherName.of("Carlos"));
        Teacher teacher2 = Teacher.create(TeacherId.generate(), TeacherName.of("Carlos"));
        assertNotEquals(teacher1, teacher2);
    }
}
