package com.masorange.registry.application.classgroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.infrastructure.classgroup.InMemoryClassGroupRepository;
import org.junit.Test;

public class CreateClassGroupTest {

    @Test
    public void shouldCreateGroupWithTutorAndNoStudents() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        CreateClassGroup createClassGroup = CreateClassGroup.create(repository);
        TeacherId tutorId = TeacherId.generate();
        ClassGroup group = createClassGroup.execute("1A", tutorId);
        assertEquals("1A", group.name().value());
        assertEquals(tutorId, group.tutorId());
        assertTrue(group.students().isEmpty());
        assertTrue(repository.findById(group.id()).isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptyName() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        CreateClassGroup createClassGroup = CreateClassGroup.create(repository);
        createClassGroup.execute("", TeacherId.generate());
    }
}
