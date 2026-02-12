package com.miempresa;

import utils.Calculadora;
import com.google.gson.Gson; 
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
        
        Saludo saludo = new Saludo();
        Calculadora calc = new Calculadora();
        int resultadoSuma = calc.sumar(10, 5);

        // Preparamos los datos en un mapa
        Map<String, Object> datos = new HashMap<>();
        datos.put("autor", "Jacqueline");
        datos.put("mensaje", saludo.obtenerMensaje());
        datos.put("saludo_personal", saludo.saludarA("Jacqueline"));
        datos.put("calculo_suma", resultadoSuma);
        datos.put("exito", true);

        // Convertimos el mapa a texto JSON
        Gson gson = new Gson();
        String json = gson.toJson(datos);

        // 4. Imprimimos el resultado final
        System.out.println("--- Resultado en formato JSON ---");
        System.out.println(json);
    }
}