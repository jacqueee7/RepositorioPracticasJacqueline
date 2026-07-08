package pkg.registry.application.student;
import pkg.registry.domain.student.*;
import java.util.*;
public class StudentRepositoryStub implements StudentRepository {
    private final Map<StudentId, Student> students = new HashMap<>();
    @Override public void save(Student student) { students.put(student.id(), student); }
    @Override public Optional<Student> findById(StudentId id) { return Optional.ofNullable(students.get(id)); }
}
