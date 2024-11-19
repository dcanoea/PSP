/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apartado2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class lenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numPalabras;
        String nombreArchivo;
        RandomAccessFile raf = null;

        try {
            // Comprueba si se reciben los argumentos necesarios en la linea de comandos
            if (args.length != 2) {
                System.out.println("Uso: java -jar lenguaje numPalabras nombreArchivo");
                return;//Sale del programa si no se han proporcionado los argumentos necesarios
            }
            //Recoge el número de palabras introducido como argumento en la variable numPalabras
            numPalabras = Integer.parseInt(args[0]);
            //Recoge el nombre del archivo introducido como argumento en la variable nombreArchivo
            nombreArchivo = args[1];

            //Preparamos el acceso al fichero
            File archivo = new File(nombreArchivo);
            if (!archivo.exists()) {//si no existe el fichero
                try {
                    archivo.createNewFile(); //Creamos el archivo
                    raf = new RandomAccessFile(archivo, "rw"); //Abrimos el fichero en modo lectura y escritura
                    for (int i = 0; i < numPalabras; i++) {
                        raf.writeChars("Palabra " +i + "- " + palabraAleatoria(8));
                        raf.writeBytes(System.lineSeparator()); // Salto de línea

                    }
                } catch (Exception e) {
                    System.out.println("Erros al escribir en el fichero");
                } finally {
                    if (raf != null) {
                        try {
                            raf.close(); //nos aseguramos que se cierra el fichero
                        } catch (IOException ex) {
                            System.out.println("Error al cerrar el fichero");
                            System.exit(1); //Si hay error, cerramos el programa
                        }

                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("El primer argumento debe ser un número entero válido.");
        }
    }

    public static String palabraAleatoria(int length) {
        String abecedario = "abcdefghijklmnopqrstuvwxyz";
        String mayusculas = abecedario.toUpperCase();
        String alfabeto = abecedario + mayusculas;

        Random random = new Random();

        String resultado = "";
        for (int i = 0; i < length; i++) {
            int posicion = random.nextInt(alfabeto.length());
            char caracter = alfabeto.charAt(posicion);
            resultado = resultado + caracter;
        }
        return resultado;
    }
}
