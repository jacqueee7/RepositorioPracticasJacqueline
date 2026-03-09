package com.masorange.registry.application.classgroup;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import com.masorange.registry.domain.classgroup.ClassGroupName;
import com.masorange.registry.domain.teacher.TeacherId;

public final class CreateClassGroup {

    private final ClassGroupRepository repository;

    private CreateClassGroup(ClassGroupRepository repository) {
        this.repository = repository;
    }

    public static CreateClassGroup create(ClassGroupRepository repository) {
        return new CreateClassGroup(repository);
    }

    public ClassGroup execute(String name, TeacherId tutorId) {
        ClassGroupId id = ClassGroupId.generate();
        ClassGroupName groupName = ClassGroupName.of(name);
        ClassGroup classGroup = ClassGroup.create(id, groupName, tutorId);
        repository.save(classGroup);
        return classGroup;
    }
}
