package com.masorange.registry.application.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.masorange.registry.domain.student.Student;
import com.masorange.registry.infrastructure.student.InMemoryStudentRepository;
import org.junit.Test;

public class CreateStudentTest {

    @Test
    public void shouldCreateAndPersistStudent() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        CreateStudent createStudent = CreateStudent.create(repository);
        Student student = createStudent.execute("Ana Garcia");
        assertNotNull(student.id());
        assertEquals("Ana Garcia", student.name().value());
        assertTrue(repository.findById(student.id()).isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptyName() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        CreateStudent createStudent = CreateStudent.create(repository);
        createStudent.execute("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWithNullName() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        CreateStudent createStudent = CreateStudent.create(repository);
        createStudent.execute(null);
    }
}
