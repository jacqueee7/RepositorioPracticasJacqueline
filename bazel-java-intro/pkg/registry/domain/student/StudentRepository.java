package pkg.registry.domain.student;
import java.util.Optional;
public interface StudentRepository {
    void save(Student student);
    Optional<Student> findById(StudentId id);
}
