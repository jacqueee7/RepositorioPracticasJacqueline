package utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculadoraTest {

    @Test
    public void testSumaPositivos() {
        Calculadora calc = new Calculadora();
        // Esperamos que 10 + 5 sea 15
        assertEquals(15, calc.sumar(10, 5));
    }

    @Test
    public void testSumaNegativos() {
        Calculadora calc = new Calculadora();
        // Esperamos que -5 + (-5) sea -10
        assertEquals(-10, calc.sumar(-5, -5));
    }

    @Test
    public void testSumaCero() {
        Calculadora calc = new Calculadora();
        // Esperamos que 100 + 0 sea 100
        assertEquals(100, calc.sumar(100, 0));
    }
}