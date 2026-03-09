package com.masorange.grading.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubjectTest {

    @Test
    public void shouldCreateWithValidName() {
        Subject subject = Subject.of("Mathematics");

        assertEquals("Mathematics", subject.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        Subject subject = Subject.of("  Mathematics  ");

        assertEquals("Mathematics", subject.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        Subject.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        Subject.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        Subject.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        Subject subject1 = Subject.of("Mathematics");
        Subject subject2 = Subject.of("Mathematics");

        assertEquals(subject1, subject2);
        assertEquals(subject1.hashCode(), subject2.hashCode());
    }
}
