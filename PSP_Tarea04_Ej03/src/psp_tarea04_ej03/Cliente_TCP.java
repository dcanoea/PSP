/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_tarea04_ej03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class Cliente_TCP {

    /**
     * @param args the command line arguments
     */
    static final String host = "localhost";
    static final int puerto = 2000;

    public static void main(String[] args) {
        try {
            //Se establece una conexión al servidor en el puerto 2000 de la máquina local (localhost)
            Socket sCliente = new Socket(host, puerto);

            Scanner s = new Scanner(System.in);

            //Flujo de entrada para leer los datos del servidor
            InputStream input = sCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);

            //Iniciamos el flujo de salida
            OutputStream output = sCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);

            //Autenticación del usuario
            System.out.println("Introduce tu usuario");
            String user = s.nextLine();
            flujoSalida.writeUTF(user);
            System.out.println("Introduce tu password");
            String pass = s.nextLine();
            flujoSalida.writeUTF(pass);

            //Recogemos la respuesta del Servidor
            String respuesta = flujoEntrada.readUTF();
            if (respuesta.equals("Autenticacion OK")) {
                System.out.println("Autenticacion correcta");
                //Nombre del fichero a buscar pasado por consola
                System.out.println("Introduce el nombre del fichero a buscar: ");
                String ficheroBuscado = s.nextLine();

                //Flujo de salida  para enviar el nombre del fichero al servidor
                flujoSalida.writeUTF(ficheroBuscado); //se envia el nombre del fichero al servidor

                //Leer la respuesta del servidor
                respuesta = flujoEntrada.readUTF();
                if (respuesta.equals("Encontrado")) {
                    System.out.println("Fichero encontrado. Contenido del fichero:");
                    String linea;
                    while (!(linea = flujoEntrada.readUTF()).equals("EOF")) {
                        System.out.println(linea);
                    }
                } else {
                    System.out.println(respuesta);  // Mensaje de error si el archivo no existe
                }
            } else {
                System.out.println("Error al autenticar");
            }

            //Cerramos todos los streams y sockets
            flujoSalida.close();
            output.close();
            flujoEntrada.close();
            input.close();
            sCliente.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
