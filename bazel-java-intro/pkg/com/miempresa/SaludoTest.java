package com.miempresa;

// IMPORTANTE: Estas son las importaciones correctas para JUnit 4
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SaludoTest {

    @Test
    public void testObtenerMensaje() {
        Saludo saludo = new Saludo();

        assertEquals("Hola desde Bazel con Test", saludo.obtenerMensaje());
    }
}