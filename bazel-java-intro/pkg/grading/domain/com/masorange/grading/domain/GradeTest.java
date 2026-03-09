package com.masorange.grading.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GradeTest {

    @Test
    public void shouldCreateWithValidValue() {
        Grade grade = Grade.of(7.5);

        assertEquals(7.5, grade.value(), 0.001);
    }

    @Test
    public void shouldAcceptMinimumValue() {
        Grade grade = Grade.of(0.0);

        assertEquals(0.0, grade.value(), 0.001);
    }

    @Test
    public void shouldAcceptMaximumValue() {
        Grade grade = Grade.of(10.0);

        assertEquals(10.0, grade.value(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBelowMinimum() {
        Grade.of(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectAboveMaximum() {
        Grade.of(10.1);
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        Grade grade1 = Grade.of(8.0);
        Grade grade2 = Grade.of(8.0);

        assertEquals(grade1, grade2);
        assertEquals(grade1.hashCode(), grade2.hashCode());
    }
}
