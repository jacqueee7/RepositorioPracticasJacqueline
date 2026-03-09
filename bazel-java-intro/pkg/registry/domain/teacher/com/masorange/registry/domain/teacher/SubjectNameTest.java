package com.masorange.registry.domain.teacher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubjectNameTest {

    @Test
    public void shouldCreateWithValidName() {
        SubjectName name = SubjectName.of("Mathematics");
        assertEquals("Mathematics", name.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        SubjectName name = SubjectName.of("  Mathematics  ");
        assertEquals("Mathematics", name.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        SubjectName.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        SubjectName.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        SubjectName.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        SubjectName name1 = SubjectName.of("Mathematics");
        SubjectName name2 = SubjectName.of("Mathematics");
        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
