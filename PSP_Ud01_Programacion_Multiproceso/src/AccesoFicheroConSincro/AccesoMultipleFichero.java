package AccesoFicheroConSincro;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author David Cano Escario
 */
public class AccesoMultipleFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int orden = 0;
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        int valor = 0;
        //Comprobamos si estamos recibiendo argumentos en la l�nea de comandos
        if (args.length > 0) {
            orden = Integer.parseInt(args[0]);
            //N�mero de orden de creaci�n de este proceso
            try {
                //Rediregimos salida y error est�ndar a un fichero
                PrintStream ps = new PrintStream(
                        new BufferedOutputStream(new FileOutputStream(
                                new File("javalog.txt"), true)), true);
                System.setOut(ps);
                System.setErr(ps);
            } catch (Exception e) {
                System.err.println("P" + orden + " No he podido redirigir salidas.");
            }
        }
        //Identificamos el sistema operativo para poder acceder por su ruta al
        //fichero de forma correcta.
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) { //Windows
            if (args.length > 1) {
                nombreFichero = args[1];
            } //Hemos recibido la ruta del fichero en la l�nea de comandos
            else {
                nombreFichero = "valor.txt";
                //Fichero que se utilizar� por defecto
            }
        } else { //GNU/Linux
            if (args.length > 1) {
                nombreFichero = args[1];
            } //Hemos recibido la ruta del fichero en la l�nea de comandos
            else {
                nombreFichero = "valor.txt";
                //Fichero que se utilizar� por defecto
            }
        }
        //Preparamos el acceso al fichero
        archivo = new File(nombreFichero);
        for (int i = 0; i < 100; i++)//aumentamos las situaciones de concurrencia
         try {
            raf = new RandomAccessFile(archivo, "rwd"); //Abrimos el fichero
            //***************
            //Secci�n cr�tica
            bloqueo = raf.getChannel().lock();
            //bloqueamos el canal de acceso al fichero. Obtenemos el objeto que
            //representa el bloqueo para despu�s poder liberarlo
            System.out.println("Proceso" + orden
                    + ": ENTRA secci�n");
            // Lectura del fichero
            valor = raf.readInt(); //leemos el valor
            valor++; //incrementamos
            raf.seek(0); //volvemos a colocarnos al principio del fichero
            raf.writeInt(valor); //escribimos el valor - NO SE ESCRIBE DE MANERA LEGIBLE, POR ESO PARECE QUE NO FUNCIONA.
            System.out.println("Proceso" + orden
                    + ": SALE secci�n");
            bloqueo.release(); //Liberamos el bloqueo del canal del fichero
            bloqueo = null;
            //Fin secci�n cr�tica
            //*******************
            System.out.println("Proceso" + orden
                    + ": valor escrito " + valor);
        } catch (Exception e) {
            System.err.println("P" + orden + " Error al acceder al fichero");
            System.err.println(e.toString());
        } finally {
            try {
                if (null != raf) {
                    raf.close();
                }
                if (null != bloqueo) {
                    bloqueo.release();
                }
            } catch (Exception e2) {
                System.err.println("P" + orden + " Error al cerrar el fichero");
                System.err.println(e2.toString());
                System.exit(1);  //Si hay error, finalizamos
            }
        }

    }
}