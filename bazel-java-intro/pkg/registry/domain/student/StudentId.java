package pkg.registry.domain.student;
import java.util.UUID;
public record StudentId(String value) {
    public static StudentId of(String value) { return new StudentId(value); }
    public static StudentId generate() { return new StudentId(UUID.randomUUID().toString()); }
}
