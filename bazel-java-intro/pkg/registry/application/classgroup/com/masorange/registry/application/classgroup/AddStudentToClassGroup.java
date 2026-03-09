package com.masorange.registry.application.classgroup;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import com.masorange.registry.domain.student.StudentId;

public final class AddStudentToClassGroup {

    private final ClassGroupRepository repository;

    private AddStudentToClassGroup(ClassGroupRepository repository) {
        this.repository = repository;
    }

    public static AddStudentToClassGroup create(ClassGroupRepository repository) {
        return new AddStudentToClassGroup(repository);
    }

    public ClassGroup execute(ClassGroupId classGroupId, StudentId studentId) {
        ClassGroup classGroup = repository.findById(classGroupId)
                .orElseThrow(() -> new IllegalStateException(
                        "ClassGroup not found: " + classGroupId));
        ClassGroup updated = classGroup.addStudent(studentId);
        repository.save(updated);
        return updated;
    }
}
