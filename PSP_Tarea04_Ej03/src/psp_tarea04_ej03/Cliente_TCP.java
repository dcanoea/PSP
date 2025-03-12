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

            //Flujo de entrada para leer los datos del servidor
            InputStream input = sCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);

            //Flujo de salida  para comunicarse con el servidor
            OutputStream output = sCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);

            //Scanner para introducir datos por consola
            Scanner s = new Scanner(System.in);

            System.out.println("Conectado al servidor. Indroduce el comando (ls/get/exit)");
            while (true) {
                // Leer el comando del usuario
                System.out.print("Comando: ");
                String comando = s.nextLine();

                // Enviar el comando al servidor
                flujoSalida.writeUTF(comando);

                // Manejar la respuesta del servidor según el comando
                switch (comando.toLowerCase()) {
                    case "ls":
                        // Solicitar el nombre del directorio
                        System.out.print("Introduce el nombre del directorio: ");
                        String directorio = s.nextLine();
                        flujoSalida.writeUTF(directorio);

                        if (flujoEntrada.readUTF().equals("El directorio no existe")) {
                            break;
                        } else {
                            // Recibir y mostrar la lista de archivos
                            System.out.println("Contenido del directorio:");
                            String listaArchivos;
                            while (!(listaArchivos = flujoEntrada.readUTF()).equals("EOF")) {
                                System.out.println(listaArchivos);
                            }
                            break;
                        }

                    case "get":
                        // Solicitar el nombre del archivo
                        System.out.print("Introduce el nombre del archivo: ");
                        String archivo = s.nextLine();
                        flujoSalida.writeUTF(archivo);

                        // Recibir la respuesta del servidor
                        String respuesta = flujoEntrada.readUTF();
                        if (respuesta.equals("Encontrado")) {
                            System.out.println("Archivo encontrado. Contenido del archivo:");
                            String linea;
                            while (!(linea = flujoEntrada.readUTF()).equals("EOF")) {
                                System.out.println(linea);
                            }
                        } else {
                            System.out.println(respuesta); // Mensaje de error si el archivo no existe
                        }
                        break;

                    case "exit":
                        System.out.println("Cerrando conexión con el servidor...");
                        return; // Salir del bucle y cerrar el cliente

                    default:
                        System.out.println("Comando no reconocido. Usa 'ls', 'get' o 'exit'.");
                        break;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
