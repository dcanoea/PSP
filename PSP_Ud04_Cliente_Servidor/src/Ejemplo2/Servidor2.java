/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplo2;

/**
 *
 * @author David Cano Escario
 */
import java.io.*;
import java.net.*;

class Servidor2 extends Thread {

    Socket skCliente;
    static final int Puerto = 2000;

    public Servidor2(Socket sCliente) {
        skCliente = sCliente;
    }

    public static void main(String[] arg) {
        try {
            // Inicio el servidor en el puerto
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);

            while (true) {
                // Se conecta un cliente
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado");
                // Atiendo al cliente mediante un thread
                new Servidor2(skCliente).start();
            }
        } catch (Exception e) {;
        }
    }

    public void run() {
        try {
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICION DEL CLIENTE
            flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");

            // Se cierra la conexion
            skCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
