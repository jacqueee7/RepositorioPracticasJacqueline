package pkg.registry.application.student;
import pkg.registry.domain.student.*;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;
public class CreateStudentTest {
    @Test public void should_create_student() {
        StudentRepositoryStub repository = new StudentRepositoryStub(); 
        CreateStudent useCase = new CreateStudent(repository);
        StudentId id = StudentId.generate();
        StudentName name = StudentName.of("Jacqueline");
        useCase.execute(id.value(), name.value());
        Optional<Student> student = repository.findById(id);
        assertTrue(student.isPresent());
        assertEquals("Jacqueline", student.get().name().value());
    }
}
