/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apartado2;

/**
 *
 * @author David Cano Escario
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class colaborar {

    public static void main(String[] args) {
        String nombreArchivo = args[0]; // El nombre del archivo se recibe como el primer parámetro

        try {
            for (int i = 1; i <= 10; i++) {
                int numPalabras = i * 10; // Cantidad de palabras para esta instancia

                // Construir el comando para ejecutar el JAR con argumentos
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "lenguaje.jar", String.valueOf(numPalabras), nombreArchivo);

                // Lanzar el proceso
                pb.inheritIO(); // Para heredar la consola (opcional, útil para depuración)
                Process proceso = pb.start();

                // Esperar a que el proceso termine antes de iniciar el siguiente
                int exitCode = proceso.waitFor();
                if (exitCode != 0) {
                    System.out.println("Instancia " + i + " terminó con errores (código " + exitCode + ").");
                } else {
                    System.out.println("Instancia " + i + " completada con éxito.");
                }
            }

            System.out.println("Proceso completado. Revisa el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al ejecutar una instancia de 'lenguaje': " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Ejecución interrumpida: " + e.getMessage());
        }
    }
}
