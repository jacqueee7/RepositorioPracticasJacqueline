package com.masorange.registry.infrastructure.student.integration;

import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.student.StudentName;
import com.masorange.registry.infrastructure.student.InMemoryStudentRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentRepositoryIntegrationTest {

    @Test
    public void testSaveAndRetrieveFromSqlite() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        
        StudentId id = StudentId.generate();
        StudentName name = StudentName.of("Prueba Integracion");
        Student student = Student.create(id, name);

        repository.save(student);

        Optional<Student> retrievedOpt = repository.findById(id);
        
        assertTrue("El estudiante debe existir en la base de datos", retrievedOpt.isPresent());
        
        Student retrievedStudent = retrievedOpt.get();
        assertEquals("Prueba Integracion", retrievedStudent.name().value());
        assertEquals(id.value(), retrievedStudent.id().value());
    }
}
