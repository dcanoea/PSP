/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejemplo3;

/**
 *
 * @author David Cano Escario
 */
import java.io.*;
import java.net.*;

public class Cliente3 {

    static final String SERVIDOR = "localhost";
    static final int PUERTO = 2000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO); BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream())); DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream()); BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor en " + SERVIDOR + ":" + PUERTO);
            String mensajeServidor;

            // Recibe el primer mensaje del servidor
            while ((mensajeServidor = entradaServidor.readLine()) != null) {
                System.out.println("SERVIDOR: " + mensajeServidor);

                if (mensajeServidor.equals("Conexion terminada.")) {
                    break;
                }

                System.out.print("> "); // Indica que el cliente puede escribir
                String comando = teclado.readLine();
                salidaServidor.writeUTF(comando);
                salidaServidor.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
