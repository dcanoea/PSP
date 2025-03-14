/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_tarea04_ej03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author David Cano Escario
 */
public class Cliente_TCP {

    static final String host = "localhost";
    static final int puerto = 2000;

    public static void main(String[] args) {
        try {
            // Conexión con el servidor
            Socket sCliente = new Socket(host, puerto);
            Scanner s = new Scanner(System.in);

            // Flujos de entrada y salida
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());

            // Autenticación del usuario
            System.out.println("Introduce tu usuario:");
            String user = s.nextLine();
            flujoSalida.writeUTF(user);

            System.out.println("Introduce tu password:");
            String pass = s.nextLine();
            flujoSalida.writeUTF(pass);

            // Respuesta del servidor
            String respuesta = flujoEntrada.readUTF();
            if (respuesta.equals("Autenticacion OK")) {
                System.out.println("Autenticacion correcta");

                // Bucle de comandos
                while (true) {
                    System.out.println(flujoEntrada.readUTF());
                    String comando = s.nextLine();
                    flujoSalida.writeUTF(comando);

                    if (comando.equals("ls")) {
                        // El servidor solicita el directorio
                        System.out.println(flujoEntrada.readUTF());
                        String directorio = s.nextLine();
                        flujoSalida.writeUTF(directorio);

                        // Recibir lista de archivos
                        String linea;
                        while (!(linea = flujoEntrada.readUTF()).equals("EOF")) {
                            System.out.println(linea);
                        }
                    } else if (comando.equals("get")) {
                        System.out.println(flujoEntrada.readUTF());
                        String ficheroBuscado = s.nextLine();
                        flujoSalida.writeUTF(ficheroBuscado);

                        respuesta = flujoEntrada.readUTF();
                        if (respuesta.equals("Encontrado")) {
                            System.out.println("Fichero encontrado. Contenido del fichero:");
                            String linea;
                            while (!(linea = flujoEntrada.readUTF()).equals("EOF")) {
                                System.out.println(linea);
                            }
                        } else {
                            System.out.println(respuesta);
                        }
                    } else if (comando.equals("exit")) {
                        System.out.println("Desconectado");
                        flujoSalida.close();
                        flujoEntrada.close();
                        sCliente.close();
                        break;
                    } else {
                        System.out.println("Comando no reconocido");
                        break;
                    }
                }
            } else {
                System.out.println("Error al autenticar");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
