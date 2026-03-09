package com.masorange.registry.application.classgroup;

import static org.junit.Assert.assertEquals;

import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import com.masorange.registry.domain.classgroup.ClassGroupName;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.infrastructure.classgroup.InMemoryClassGroupRepository;
import org.junit.Test;

public class AddStudentToClassGroupTest {

    @Test
    public void shouldAddStudentToExistingGroup() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        ClassGroup group = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        repository.save(group);
        AddStudentToClassGroup addStudent = AddStudentToClassGroup.create(repository);
        StudentId studentId = StudentId.generate();
        ClassGroup updated = addStudent.execute(group.id(), studentId);
        assertEquals(1, updated.students().size());
        assertEquals(studentId, updated.students().get(0));
    }

    @Test
    public void shouldAddMultipleStudents() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        ClassGroup group = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        repository.save(group);
        AddStudentToClassGroup addStudent = AddStudentToClassGroup.create(repository);
        addStudent.execute(group.id(), StudentId.generate());
        ClassGroup updated = addStudent.execute(group.id(), StudentId.generate());
        assertEquals(2, updated.students().size());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenGroupNotFound() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        AddStudentToClassGroup addStudent = AddStudentToClassGroup.create(repository);
        addStudent.execute(ClassGroupId.generate(), StudentId.generate());
    }
}
