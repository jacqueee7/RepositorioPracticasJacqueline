package com.miempresa;

// IMPORTANTE: Estas son las importaciones correctas para JUnit 4
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.beans.Transient;

public class SaludoTest {

    @Test
    public void testObtenerMensaje() {
        Saludo saludo = new Saludo();

        assertEquals("Hola desde Bazel con Test", saludo.obtenerMensaje());
    }

    @Test
    public void testSaludarConNombre() {
        Saludo saludo = new Saludo();

        assertEquals("Hola, Jacqueline!", saludo.saludarA("Jacqueline"));
        assertEquals("Hola, Bazel!", saludo.saludarA("Bazel"));
    }

    @Test
    public void testSaludarVacio(){
        Saludo saludo = new Saludo();
        assertEquals("Hola, ¿cómo estás?", saludo.saludarA(""));
        assertEquals("Hola, ¿cómo estás?", saludo.saludarA("   "));
    }

    @Test
    public void testSaludarNull(){
        Saludo saludo = new Saludo();
        assertEquals("Hola, ¿cómo estás?", saludo.saludarA(null));
    }
}

//Ignoro los fallos de cobertura de código en esta clase de prueba, ya que el objetivo principal es verificar la funcionalidad básica del saludo.