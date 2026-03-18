package com.masorange.registry.application.classgroup;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import com.masorange.registry.domain.student.StudentId;
import java.util.List;

public final class ListStudentsByClassGroup {
    private final ClassGroupRepository repository;

    private ListStudentsByClassGroup(ClassGroupRepository repository) {
        this.repository = repository;
    }

    public static ListStudentsByClassGroup create(ClassGroupRepository repository) {
        return new ListStudentsByClassGroup(repository);
    }

    public List<StudentId> execute(ClassGroupId classGroupId) {
        return repository.findById(classGroupId)
                .map(ClassGroup::students)
                .orElseThrow(() -> new IllegalArgumentException("Class group not found: " + classGroupId.value()));
    }
}