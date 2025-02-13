
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author infoe
 */
public class Lector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Vamos a leer de la entrada estándar del proceso y escribir
        // los datos que se reciben en la salida estándar del proceso.

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader (isr);
        // Obtenemos el stream de lectura de la entrada estándar
        // utilizamos un lector con Buffered, para no perder ningún dato
        String lineaTeclado = null;
        try{
            System.out.println("Proceso lector");
            //Mostramos que el proceso que está escribiendo es el que está
            //leyendo los datos.
            while ((lineaTeclado = bf.readLine())!= null){
                //Vamos leyendo y mostrando los datos
                System.out.println(lineaTeclado);
            }
        }catch(IOException ex){
            System.err.println("Se ha producido un error de E/S.");
            System.err.println(ex.toString());
        }
    }

    
}
