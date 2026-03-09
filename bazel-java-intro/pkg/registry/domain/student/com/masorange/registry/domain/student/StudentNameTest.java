package com.masorange.registry.domain.student;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentNameTest {

    @Test
    public void shouldCreateWithValidName() {
        StudentName name = StudentName.of("Ana Garcia");
        assertEquals("Ana Garcia", name.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        StudentName name = StudentName.of("  Ana Garcia  ");
        assertEquals("Ana Garcia", name.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        StudentName.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        StudentName.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        StudentName.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        StudentName name1 = StudentName.of("Ana Garcia");
        StudentName name2 = StudentName.of("Ana Garcia");
        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
