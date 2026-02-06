package com.miempresa;
import utils.Calculadora;

public class Main {
    public static void main(String[] args) {
        //System.out.println("¡Hola, Mundo!");
       Saludo saludo = new Saludo();
       System.out.println(saludo.obtenerMensaje());
       System.out.println(saludo.saludarA("Jacqueline"));

        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(10, 5);
        System.out.println("El resultado de la suma es: " + resultado);

    }
}