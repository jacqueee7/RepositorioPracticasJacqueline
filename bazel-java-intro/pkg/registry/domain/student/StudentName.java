package pkg.registry.domain.student;
public record StudentName(String value) {
    public static StudentName of(String value) { return new StudentName(value); }
}
