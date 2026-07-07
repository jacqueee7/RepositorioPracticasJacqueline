package com.masorange.registry.application.student;

import com.masorange.registry.domain.student.Student;
import com.masorange.registry.infrastructure.student.InMemoryStudentRepository;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateStudentTest {

    @Test
    public void testCreateStudentSuccessfully() {
        // 1. Instanciamos tu repositorio (que por dentro usa SQLite)
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        
        // 2. ¡CORREGIDO! Usamos el método factoría porque el constructor es privado
        CreateStudent useCase = CreateStudent.create(repository);

        // 3. Ejecutamos y guardamos el resultado
        Student student = useCase.execute("Alice"); 
        
        // 4. Verificamos que todo haya ido bien
        assertNotNull(student);
        assertEquals("Alice", student.name().value());
    }
}