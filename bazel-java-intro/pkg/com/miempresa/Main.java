package com.miempresa;

import utils.Calculadora;
import com.google.gson.Gson; 
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
        int num1 = 10; int num2 = 5; String nombre = "Jacqueline"; // Valores poor defecto, si no se escribe nada se utilizan estos.

        if (args.length >= 2){
            try {
                num1 = Integer.parseInt(args[0]);
                num2 = Integer.parseInt(args[1]);
                
            } catch (NumberFormatException e) {
                System.err.println("Error al parsear los números. Usando valores por defecto.");
                return;
            }
      
        }

        if (args.length >= 3){ 
            nombre = args[2];
        }

        Saludo saludo = new Saludo();
        Calculadora calc = new Calculadora();
        int resultadoSuma = calc.sumar(num1, num2);

        // Preparamos los datos en un mapa
        Map<String, Object> datos = new HashMap<>();
        datos.put("autor", nombre);
        datos.put("mensaje", saludo.obtenerMensaje());
        datos.put("saludo_personal", saludo.saludarA(nombre));
        datos.put("operacion", num1 + " + " + num2);
        datos.put("resultado_suma", resultadoSuma);
        datos.put("exito", true);


        // Convertimos el mapa a texto JSON
        Gson gson = new Gson();
        System.out.println(gson.toJson(datos));

    }
}