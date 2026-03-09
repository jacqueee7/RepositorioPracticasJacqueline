package com.masorange.registry.application.classgroup;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import java.util.Optional;

public interface ClassGroupRepository {

    void save(ClassGroup classGroup);

    Optional<ClassGroup> findById(ClassGroupId id);
}
