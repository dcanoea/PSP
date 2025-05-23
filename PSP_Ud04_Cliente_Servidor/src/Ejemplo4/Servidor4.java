/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejemplo4;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 *
 * @author David Cano Escario
 */
public class Servidor4 {

    static final int Puerto = 2000;

    public Servidor4() {

        try {
            // Inicio el servidor en el puerto
            ServerSocket sServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);

            // Se conecta un cliente
            Socket sCliente = sServidor.accept(); // Crea objeto
            System.out.println("Cliente conectado");

            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(sCliente.getOutputStream());

            // CUERPO DEL ALGORITMO
            long tiempo1 = (new Date()).getTime();
            flujo_salida.writeUTF(Long.toString(tiempo1));

            // Se cierra la conexi�n
            sCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Servidor4();
    }

}
