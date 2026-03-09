package com.masorange.registry.domain.teacher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeacherNameTest {

    @Test
    public void shouldCreateWithValidName() {
        TeacherName name = TeacherName.of("Carlos Lopez");
        assertEquals("Carlos Lopez", name.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        TeacherName name = TeacherName.of("  Carlos Lopez  ");
        assertEquals("Carlos Lopez", name.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        TeacherName.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        TeacherName.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        TeacherName.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        TeacherName name1 = TeacherName.of("Carlos Lopez");
        TeacherName name2 = TeacherName.of("Carlos Lopez");
        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
