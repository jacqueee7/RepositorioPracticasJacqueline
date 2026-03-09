package com.masorange.registry.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.masorange.registry.application.classgroup.AddStudentToClassGroup;
import com.masorange.registry.application.classgroup.ClassGroupRepository;
import com.masorange.registry.application.classgroup.CreateClassGroup;
import com.masorange.registry.application.student.CreateStudent;
import com.masorange.registry.application.student.StudentRepository;
import com.masorange.registry.application.teacher.AssignSubject;
import com.masorange.registry.application.teacher.CreateTeacher;
import com.masorange.registry.application.teacher.TeacherRepository;
import com.masorange.registry.application.teacher.TeachingAssignmentRepository;
import com.masorange.registry.domain.classgroup.ClassGroup;
import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.teacher.Teacher;
import com.masorange.registry.infrastructure.classgroup.InMemoryClassGroupRepository;
import com.masorange.registry.infrastructure.student.InMemoryStudentRepository;
import com.masorange.registry.infrastructure.teacher.InMemoryTeacherRepository;
import com.masorange.registry.infrastructure.teacher.InMemoryTeachingAssignmentRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Starting Registry application");

        StudentRepository studentRepository = new InMemoryStudentRepository();
        TeacherRepository teacherRepository = new InMemoryTeacherRepository();
        TeachingAssignmentRepository assignmentRepository = new InMemoryTeachingAssignmentRepository();
        ClassGroupRepository classGroupRepository = new InMemoryClassGroupRepository();

        CreateStudent createStudent = CreateStudent.create(studentRepository);
        CreateTeacher createTeacher = CreateTeacher.create(teacherRepository);
        AssignSubject assignSubject = AssignSubject.create(assignmentRepository);
        CreateClassGroup createClassGroup = CreateClassGroup.create(classGroupRepository);
        AddStudentToClassGroup addStudentToClassGroup = AddStudentToClassGroup.create(classGroupRepository);

        Teacher teacher = createTeacher.execute("Carlos Lopez");
        LOG.info("Created teacher: {}", teacher.name().value());

        assignSubject.execute(teacher.id(), "Mathematics");
        assignSubject.execute(teacher.id(), "Physics");

        Student student = createStudent.execute("Ana Garcia");
        LOG.info("Created student: {}", student.name().value());

        ClassGroup classGroup = createClassGroup.execute("1A", teacher.id());
        classGroup = addStudentToClassGroup.execute(classGroup.id(), student.id());
        LOG.info("Created class group: {} with {} students", classGroup.name().value(), classGroup.students().size());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("student", student.name().value());
        result.put("studentId", student.id().value());
        result.put("teacher", teacher.name().value());
        result.put("teacherId", teacher.id().value());
        result.put("classGroup", classGroup.name().value());
        result.put("classGroupStudents", classGroup.students().size());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(result));

        LOG.info("Registry application completed");
    }
}
