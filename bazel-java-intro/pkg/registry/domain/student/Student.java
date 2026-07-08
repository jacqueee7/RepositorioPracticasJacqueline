package pkg.registry.domain.student;
public class Student {
    private final StudentId id;
    private final StudentName name;
    private Student(StudentId id, StudentName name) { this.id = id; this.name = name; }
    public static Student create(StudentId id, StudentName name) { return new Student(id, name); }
    public StudentId id() { return id; }
    public StudentName name() { return name; }
}
