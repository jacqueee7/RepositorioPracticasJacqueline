package com.masorange.registry.domain.classgroup;

import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ClassGroup {

    private final ClassGroupId id;
    private final ClassGroupName name;
    private final TeacherId tutorId;
    private final List<StudentId> students;

    private ClassGroup(ClassGroupId id, ClassGroupName name, TeacherId tutorId, List<StudentId> students) {
        Objects.requireNonNull(id, "ClassGroup id must not be null");
        Objects.requireNonNull(name, "ClassGroup name must not be null");
        Objects.requireNonNull(tutorId, "ClassGroup tutorId must not be null");
        Objects.requireNonNull(students, "ClassGroup students must not be null");
        this.id = id;
        this.name = name;
        this.tutorId = tutorId;
        this.students = Collections.unmodifiableList(new ArrayList<>(students));
    }

    public static ClassGroup create(ClassGroupId id, ClassGroupName name, TeacherId tutorId) {
        return new ClassGroup(id, name, tutorId, Collections.emptyList());
    }

    public ClassGroup addStudent(StudentId studentId) {
        Objects.requireNonNull(studentId, "StudentId must not be null");
        List<StudentId> updatedStudents = new ArrayList<>(students);
        updatedStudents.add(studentId);
        return new ClassGroup(id, name, tutorId, updatedStudents);
    }

    public ClassGroupId id() {
        return id;
    }

    public ClassGroupName name() {
        return name;
    }

    public TeacherId tutorId() {
        return tutorId;
    }

    public List<StudentId> students() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassGroup)) return false;
        ClassGroup that = (ClassGroup) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ClassGroup{id=" + id + ", name=" + name + ", tutorId=" + tutorId
                + ", students=" + students.size() + "}";
    }
}
