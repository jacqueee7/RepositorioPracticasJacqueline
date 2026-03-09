package com.masorange.grading.application;

import com.masorange.grading.domain.StudentGrade;
import com.masorange.registry.domain.student.StudentId;
import java.util.List;

public interface GradeRepository {

    void save(StudentGrade studentGrade);

    List<StudentGrade> findByStudentId(StudentId studentId);
}
