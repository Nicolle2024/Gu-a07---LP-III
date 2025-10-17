package contador;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ContadorPalabras {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione un archivo de texto");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            analizarArchivo(archivo);
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }

    private static void analizarArchivo(File archivo) {
        try {
            if (!archivo.exists() || !archivo.isFile()) {
                JOptionPane.showMessageDialog(null, "El archivo no es válido.");
                return;
            }

            List<String> lineas = Files.readAllLines(archivo.toPath());
            int totalLineas = lineas.size();
            int totalPalabras = 0;
            int totalCaracteres = 0;
            Map<String, Integer> conteoPalabras = new HashMap<>();

            for (String linea : lineas) {
                totalCaracteres += linea.length();
                String[] palabras = linea.split("\\W+");

                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        palabra = palabra.toLowerCase();
                        totalPalabras++;
                        conteoPalabras.put(palabra, conteoPalabras.getOrDefault(palabra, 0) + 1);
                    }
                }
            }

            double promedio = totalLineas > 0 ? (double) totalPalabras / totalLineas : 0;

            List<Map.Entry<String, Integer>> listaFrecuentes = new ArrayList<>(conteoPalabras.entrySet());
            listaFrecuentes.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            System.out.println("===== RESULTADOS =====");
            System.out.println("Archivo: " + archivo.getName());
            System.out.println("Total de líneas: " + totalLineas);
            System.out.println("Total de palabras: " + totalPalabras);
            System.out.println("Total de caracteres: " + totalCaracteres);
            System.out.printf("Promedio de palabras por línea: %.2f%n", promedio);
            System.out.println("\nPalabras más frecuentes:");

            int limite = Math.min(10, listaFrecuentes.size());
            for (int i = 0; i < limite; i++) {
                Map.Entry<String, Integer> entrada = listaFrecuentes.get(i);
                System.out.println((i + 1) + ". " + entrada.getKey() + " → " + entrada.getValue());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }
}
