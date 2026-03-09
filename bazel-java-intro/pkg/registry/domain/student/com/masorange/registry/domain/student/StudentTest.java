package com.masorange.registry.domain.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StudentTest {

    @Test
    public void shouldCreateStudentWithIdAndName() {
        StudentId id = StudentId.generate();
        StudentName name = StudentName.of("Ana Garcia");
        Student student = Student.create(id, name);
        assertEquals(id, student.id());
        assertEquals(name, student.name());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullId() {
        Student.create(null, StudentName.of("Ana"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullName() {
        Student.create(StudentId.generate(), null);
    }

    @Test
    public void shouldBeEqualByIdOnly() {
        StudentId id = StudentId.generate();
        Student student1 = Student.create(id, StudentName.of("Ana"));
        Student student2 = Student.create(id, StudentName.of("Pedro"));
        assertEquals(student1, student2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentId() {
        Student student1 = Student.create(StudentId.generate(), StudentName.of("Ana"));
        Student student2 = Student.create(StudentId.generate(), StudentName.of("Ana"));
        assertNotEquals(student1, student2);
    }
}
