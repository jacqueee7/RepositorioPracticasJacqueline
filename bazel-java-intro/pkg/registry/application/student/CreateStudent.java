package pkg.registry.application.student;
import pkg.registry.domain.student.*;
public class CreateStudent {
    private final StudentRepository repository;
    public CreateStudent(StudentRepository repository) { this.repository = repository; }
    public void execute(String id, String name) {
        Student student = Student.create(StudentId.of(id), StudentName.of(name));
        repository.save(student);
    }
}
