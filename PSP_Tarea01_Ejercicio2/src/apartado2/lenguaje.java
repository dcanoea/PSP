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
 * Clase principal del programa que genera palabras aleatorias y las escribe en
 * un archivo
 *
 * @author David Cano Escario
 */
public class lenguaje {

    /**
     * Método principal que ejecuta el programa. Genera palabras aleatorias y
     * las escribe en un archivo cuyo nombre se pasa como argumento.
     *
     * @param args Argumentos de la línea de comandos. El primer argumento es el
     * número de palabras a generar y el segundo es el nombre del archivo donde
     * se guardarán.
     */
    public static void main(String[] args) {
        int numPalabras;
        String nombreArchivo;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;

        try {
            // Comprueba si se reciben los argumentos necesarios en la linea de comandos
            if (args.length != 2) {
                System.out.println("El comando debe ser: java -jar lenguaje numPalabras nombreArchivo");
                return;//Sale del programa si no se han proporcionado los argumentos necesarios
            }
            //Recoge el número de palabras introducido como argumento en la variable numPalabras
            numPalabras = Integer.parseInt(args[0]);
            //Recoge el nombre del archivo introducido como argumento en la variable nombreArchivo
            nombreArchivo = args[1];

            //Preparamos el acceso al fichero
            File archivo = new File(nombreArchivo);
            try {
                archivo.createNewFile(); //Creamos el archivo
                raf = new RandomAccessFile(archivo, "rw"); //Abrimos el fichero en modo lectura y escritura

                //Mediante un bucle for escribimos las palabras aleatorias (método palabraAleatoria) y un salto de línea
                for (int i = 0; i < numPalabras; i++) {
                    //***************                        
                    //Sección crítica
                    bloqueo = raf.getChannel().lock();
                    // Posicionarse al final del archivo
                    raf.seek(raf.length());
                    raf.writeChars("Palabra " + i + "- " + palabraAleatoria(8));
                    raf.writeBytes(System.lineSeparator()); // Salto de línea
                    bloqueo.release();
                    bloqueo = null;
                    //Fin sección critica
                    //***********************
                }
            } catch (Exception e) {
                System.out.println("Erros al escribir en el fichero");
            } finally {
                raf.close(); //nos aseguramos que se cierra el fichero
            }

        } catch (NumberFormatException e) {
            System.out.println("El primer argumento debe ser un número entero válido.");
        } catch (IOException ex) {
            System.out.println("Error al cerrar el fichero");
            Logger.getLogger(lenguaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera una palabra aleatoria de una longitud especificada.
     *
     * @param length La longitud de la palabra aleatoria a generar.
     * @return Una cadena de texto que contiene la palabra aleatoria.
     */
    public static String palabraAleatoria(int length) {
        String abecedario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder resultado = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int posicion = random.nextInt(abecedario.length());
            resultado.append(abecedario.charAt(posicion));
        }

        return resultado.toString();
    }
}
