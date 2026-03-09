package com.masorange.registry.domain.classgroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import org.junit.Test;

public class ClassGroupTest {

    @Test
    public void shouldCreateWithEmptyStudentList() {
        ClassGroupId id = ClassGroupId.generate();
        ClassGroupName name = ClassGroupName.of("1A");
        TeacherId tutorId = TeacherId.generate();
        ClassGroup group = ClassGroup.create(id, name, tutorId);
        assertEquals(id, group.id());
        assertEquals(name, group.name());
        assertEquals(tutorId, group.tutorId());
        assertTrue(group.students().isEmpty());
    }

    @Test
    public void shouldAddStudentReturningNewInstance() {
        ClassGroup original = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        StudentId studentId = StudentId.generate();
        ClassGroup updated = original.addStudent(studentId);
        assertTrue(original.students().isEmpty());
        assertEquals(1, updated.students().size());
        assertEquals(studentId, updated.students().get(0));
    }

    @Test
    public void shouldPreserveIdWhenAddingStudent() {
        ClassGroupId id = ClassGroupId.generate();
        ClassGroup original = ClassGroup.create(id, ClassGroupName.of("1A"), TeacherId.generate());
        ClassGroup updated = original.addStudent(StudentId.generate());
        assertEquals(id, updated.id());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullId() {
        ClassGroup.create(null, ClassGroupName.of("1A"), TeacherId.generate());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullName() {
        ClassGroup.create(ClassGroupId.generate(), null, TeacherId.generate());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullTutorId() {
        ClassGroup.create(ClassGroupId.generate(), ClassGroupName.of("1A"), null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullStudentId() {
        ClassGroup group = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        group.addStudent(null);
    }

    @Test
    public void shouldBeEqualById() {
        ClassGroupId id = ClassGroupId.generate();
        ClassGroup g1 = ClassGroup.create(id, ClassGroupName.of("1A"), TeacherId.generate());
        ClassGroup g2 = ClassGroup.create(id, ClassGroupName.of("2B"), TeacherId.generate());
        assertEquals(g1, g2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentId() {
        ClassGroup g1 = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        ClassGroup g2 = ClassGroup.create(
                ClassGroupId.generate(), ClassGroupName.of("1A"), TeacherId.generate());
        assertNotEquals(g1, g2);
    }
}
