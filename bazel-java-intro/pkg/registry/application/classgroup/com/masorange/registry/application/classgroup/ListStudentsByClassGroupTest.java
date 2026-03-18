package com.masorange.registry.application.classgroup;

import static org.junit.Assert.assertEquals;
import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.classgroup.ClassGroupId;
import com.masorange.registry.domain.classgroup.ClassGroupName;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import com.masorange.registry.infrastructure.classgroup.InMemoryClassGroupRepository;
import org.junit.Test;
import java.util.List;

public class ListStudentsByClassGroupTest {
    @Test
    public void shouldReturnStudentsOfGroup() {
        InMemoryClassGroupRepository repository = new InMemoryClassGroupRepository();
        ClassGroupId groupId = ClassGroupId.generate();
        TeacherId tutorId = TeacherId.generate();
        ClassGroup group = ClassGroup.create(groupId, ClassGroupName.of("1A"), tutorId);
        
        StudentId studentId = StudentId.generate();
        group = group.addStudent(studentId);
        repository.save(group);

        ListStudentsByClassGroup useCase = ListStudentsByClassGroup.create(repository);
        List<StudentId> result = useCase.execute(groupId);

        assertEquals(1, result.size());
        assertEquals(studentId, result.get(0));
    }
}