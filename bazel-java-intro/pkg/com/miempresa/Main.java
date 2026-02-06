package com.miempresa;

public class Main {
    public static void main(String[] args) {
        //System.out.println("¡Hola, Mundo!");
       Saludo saludo = new Saludo();
       System.out.println(saludo.obtenerMensaje());
       System.out.println(saludo.saludarA("Jacqueline"));

    }
}