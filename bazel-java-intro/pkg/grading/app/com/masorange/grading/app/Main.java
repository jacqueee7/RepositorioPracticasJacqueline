package com.masorange.grading.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masorange.grading.application.CalculateAverage;
import com.masorange.grading.application.GradeRepository;
import com.masorange.grading.application.RecordGrade;
import com.masorange.grading.infrastructure.InMemoryGradeRepository;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.teacher.TeacherId;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class Main {

    public static void main(String[] args) {
        GradeRepository gradeRepository = new InMemoryGradeRepository();

        RecordGrade recordGrade = RecordGrade.create(gradeRepository);
        CalculateAverage calculateAverage = CalculateAverage.create(gradeRepository);

        StudentId studentId = StudentId.generate();
        TeacherId teacherId = TeacherId.generate();

        recordGrade.execute(studentId, "Mathematics", 8.5, teacherId);
        recordGrade.execute(studentId, "Physics", 7.0, teacherId);
        recordGrade.execute(studentId, "Literature", 9.0, teacherId);

        double average = calculateAverage.execute(studentId);

        Map<String, String> templateValues = new HashMap<>();
        templateValues.put("studentId", studentId.value());
        templateValues.put("average", String.valueOf(average));
        StringSubstitutor substitutor = new StringSubstitutor(templateValues);
        String report = substitutor.replace("Student ${studentId} has an average grade of ${average}");
        System.out.println(report);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("studentId", studentId.value());
        result.put("teacherId", teacherId.value());
        result.put("average", average);
        result.put("subjects", 3);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(result));
    }
}
