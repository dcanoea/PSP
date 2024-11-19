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

        // Si el archivo existe se borra
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete(); // Elimina el archivo si ya existía
        }

        try {
            for (int i = 1; i <= 10; i++) {
                int numPalabras = i * 10; // Cantidad de palabras para esta instancia

                // Proceso con el comando para ejecutar lenguaje.jar
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "lenguaje.jar", String.valueOf(numPalabras), nombreArchivo);

                // Iniciamos el proceso
                Process proceso = pb.start();

                //Mostramos en consola que se ha creado otro proceso
                System.out.println("Creado proceso " + i);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(colaborar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
