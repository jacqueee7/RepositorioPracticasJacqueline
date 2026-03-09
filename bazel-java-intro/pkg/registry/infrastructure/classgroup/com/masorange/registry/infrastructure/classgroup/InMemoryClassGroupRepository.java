package com.masorange.registry.infrastructure.classgroup;

import com.masorange.registry.application.classgroup.ClassGroupRepository;
import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryClassGroupRepository implements ClassGroupRepository {

    private final Map<ClassGroupId, ClassGroup> storage = new HashMap<>();

    @Override
    public void save(ClassGroup classGroup) {
        storage.put(classGroup.id(), classGroup);
    }

    @Override
    public Optional<ClassGroup> findById(ClassGroupId id) {
        return Optional.ofNullable(storage.get(id));
    }
}
