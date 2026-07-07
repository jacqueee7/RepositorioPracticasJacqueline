package com.masorange.reporting.app;

import com.masorange.registry.domain.student.StudentId;
import com.masorange.grading.domain.Grade;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== Generando Informe de Notas (Reporting) ======");

        // 1. Instanciar los Objetos de Dominio con un formato UUID válido
        // Usamos una cadena con estructura UUID estándar para que no lance la excepción
        StudentId studentId = StudentId.of("857230aa-2574-42b6-b5e1-5e7e1c8d5047"); 
        Grade notaMatematicas = Grade.of(9.5);

        // 2. Usar Apache POI para escribir el archivo Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Informe de Notas");

            // Cabeceras
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID Estudiante");
            headerRow.createCell(1).setCellValue("Nota Final");

            // Mapeo de datos usando los métodos de acceso correctos (.value())
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(studentId.value());
            dataRow.createCell(1).setCellValue(notaMatematicas.value());

            // Autoajustar las columnas
            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar el archivo Excel en la raíz del espacio de trabajo
            String nombreArchivo = "Informe_Notas_Jacqueline.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
                workbook.write(fileOut);
            }

            System.out.println("✅ ¡Excel generado con éxito! Archivo creado: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("❌ Error creando el informe Excel: " + e.getMessage());
        }
    }
}