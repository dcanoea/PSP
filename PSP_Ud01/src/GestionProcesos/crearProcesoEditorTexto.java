/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GestionProcesos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class crearProcesoEditorTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // Obtenemos el nombre del SO
            String osName = System.getProperty("os.name").toLowerCase();
            String command;

            //Determinamos el comando según el sistema operativo
            if (osName.contains("win")) { //Windows
                command = "notepad.exe"; //Comando para abrir el bloc de notas
            } else { //Linux
                command = "gedit"; //Comando para abrir gedit
            }

            //Creamos el ProcessBuilder
            ProcessBuilder builder = new ProcessBuilder(command);

            //Si deseas redirigir la salida del proceso, puedes hacerlo
            builder.inheritIO(); //Esto redirige la salida y errores a la consola actual

            //Iniciamos el proceso
            Process proceso = builder.start();

            //Esperamos a que el proceso termine
            int exitCode = proceso.waitFor();

            //Puedes verificar si el proceso terminó correctamente
            if (exitCode == 0) {
                System.out.println("El proceso se completó con éxito");
            } else {
                System.out.println("El proceso terminó con código de salida : " + exitCode);
            }

        } catch (IOException ex) {
            System.out.println("Error al iniciar el proceso");
            Logger.getLogger(crearProcesoEditorTexto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println("Error al finalizar el proceso");
            Logger.getLogger(crearProcesoEditorTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}