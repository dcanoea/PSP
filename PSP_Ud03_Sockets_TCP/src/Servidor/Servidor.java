/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Servidor;
/**
 *
 * @author David Cano Escario
 */
import java.io.*;   
import java.net.*; 

class Servidor {
    static final int Puerto = 2000;  // Definici�n de un puerto fijo para escuchar conexiones entrantes

    public Servidor() { 
        try {
            // Se crea un objeto ServerSocket que escucha en el puerto 2000
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);  // Mensaje que indica que el servidor est� escuchando

            // Bucle para atender 3 clientes (nCli representar� al cliente atendido)
            for (int nCli = 0; nCli < 3; nCli++) {
                // Esperamos una conexi�n entrante de un cliente
                Socket sCliente = skServidor.accept();  
                System.out.println("Sirvo al cliente " + nCli);  // Mensaje que indica que se est� atendiendo al cliente

                // Creamos un flujo de salida para enviar datos al cliente
                OutputStream aux = sCliente.getOutputStream();
                DataOutputStream flujo_salida = new DataOutputStream(aux);

                // Enviamos un mensaje al cliente, personalizado con el n�mero de cliente
                flujo_salida.writeUTF("Hola cliente " + nCli);

                // Cerramos la conexi�n con el cliente
                sCliente.close();
            }
            // Mensaje que indica que todos los clientes han sido atendidos
            System.out.println("Ya se han atendido los 3 clientes");
        } catch (Exception e) {
            // En caso de error, se imprime el mensaje de error
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Servidor();  // Creamos una nueva instancia de la clase Servidor
    }
}