package com.miempresa;

public class Saludo {
    public String obtenerMensaje() {
        return "Hola desde Bazel con Test";
    }

    public String saludarA(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Hola, ¿cómo estás?";
        }
        return "Hola, " + nombre + "!";
    }


}
