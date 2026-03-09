package com.masorange.registry.domain.classgroup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassGroupNameTest {

    @Test
    public void shouldCreateWithValidName() {
        ClassGroupName name = ClassGroupName.of("1A");
        assertEquals("1A", name.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        ClassGroupName name = ClassGroupName.of("  1A  ");
        assertEquals("1A", name.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        ClassGroupName.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        ClassGroupName.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        ClassGroupName.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        ClassGroupName name1 = ClassGroupName.of("1A");
        ClassGroupName name2 = ClassGroupName.of("1A");
        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
