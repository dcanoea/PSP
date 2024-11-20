/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apartado2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * Programa para leer los archivos .txt
 *
 * @author David Cano Escario
 */
public class LecturaRandomAccesFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File fichero = new File("C:\\Users\\poker\\Documents\\NetBeansProjects\\PSP\\PSP_Tarea01_Ejercicio2\\granfichero.txt");
        try {
            RandomAccessFile archivo = new RandomAccessFile(fichero, "r");
            String linea;

            // Leer el archivo línea por línea
            while ((linea = archivo.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
