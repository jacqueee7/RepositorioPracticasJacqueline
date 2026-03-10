package com.masorange.booking.domain;

import static org.junit.Assert.assertEquals;

import java.beans.Transient;

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

    //CAMBIO 
    @Test
    public void shoulsAcceptZero() {
        Capacity capacity = Capacity.of(0);

        assertEquals(0, capacity.value());
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
