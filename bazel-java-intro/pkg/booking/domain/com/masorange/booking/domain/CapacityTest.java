package com.masorange.booking.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CapacityTest {

    @Test
    public void shouldCreateWithValidValue() {
        Capacity capacity = Capacity.of(30);

        assertEquals(30, capacity.value());
    }

    @Test
    public void shouldAcceptMinimumValue() {
        Capacity capacity = Capacity.of(1);

        assertEquals(1, capacity.value());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectZero() {
        Capacity.of(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegative() {
        Capacity.of(-5);
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        Capacity c1 = Capacity.of(30);
        Capacity c2 = Capacity.of(30);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
