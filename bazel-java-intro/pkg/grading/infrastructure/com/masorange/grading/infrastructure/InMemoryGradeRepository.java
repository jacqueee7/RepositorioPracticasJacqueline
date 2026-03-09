package com.masorange.grading.infrastructure;

import com.masorange.grading.application.GradeRepository;
import com.masorange.grading.domain.StudentGrade;
import com.masorange.registry.domain.student.StudentId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InMemoryGradeRepository implements GradeRepository {

    private final List<StudentGrade> storage = new ArrayList<>();

    @Override
    public void save(StudentGrade studentGrade) {
        storage.add(studentGrade);
    }

    @Override
    public List<StudentGrade> findByStudentId(StudentId studentId) {
        List<StudentGrade> result = new ArrayList<>();
        for (StudentGrade sg : storage) {
            if (sg.studentId().equals(studentId)) {
                result.add(sg);
            }
        }
        return Collections.unmodifiableList(result);
    }
}
