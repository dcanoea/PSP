/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_ud03_tarea03_ej01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author David Cano Escario
 */
public class Servidor_UDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numSecreto = 0;
        int numRecibido = 0;
        
        try {
            //Creamos el socket UDP en el puerto 2000 para recibir el número como mensaje
            DatagramSocket sSocket = new DatagramSocket(2000);

            //Creamos un buffer para almacenar los datos del mensaje (el número)
            byte[] cadena = new byte[4];// int ocupan 4 bytes

            //Define el objeto DatagramPacket para recibir el mensaje. Usa el buffer para almacenarlo.
            DatagramPacket mensaje = new DatagramPacket(cadena, cadena.length);

            numSecreto = (int) ((Math.random() * 100) + 1); 
            System.out.println("Adivina el número del 1 al 100");

            while (Integer.valueOf(numRecibido) != numSecreto) {
                //Recibe un paquete de datos desde el socket y lo almacena en el objeto 'mensaje'
                sSocket.receive(mensaje);

                //Convierte el mensaje recibido a una cadena, usando la longitud del mensaje
                String datos = new String(mensaje.getData(), 0, mensaje.getLength());
                
                //Cogemos el string recibido y lo convertimos a un Int
                numRecibido = Integer.valueOf(datos);
                
                //Muestra el mensaje recibido en la consola
                System.out.println("Nº recibido: " + numRecibido);
                
                //Variable string con el resultado que envíaremos de vuelta al cliente
                String respuesta;
                if (Integer.valueOf(numRecibido) < numSecreto){
                    respuesta = "El número secreto es mayor";
                } else if(Integer.valueOf(numRecibido) > numSecreto){
                    respuesta = "El número secreto es menor";
                } else {
                    respuesta = "CORRECTO!";
                }
                
                //Para envíar la respuesta al cliente, usamos el mettodo .send
                byte[] mensajeVuelta = respuesta.getBytes();
                DatagramPacket respuestaPaquete = new DatagramPacket(
                        mensajeVuelta, mensajeVuelta.length, mensaje.getAddress(), mensaje.getPort());
                sSocket.send(respuestaPaquete);
            }       
            
            System.out.println("Numero acertado. FIN DEL PROGRAMA");
            sSocket.close();
            
        } catch (SocketException e) {
            // En caso de error al crear el socket, imprime el mensaje de error
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            // En caso de error de entrada/salida al recibir los datos, imprime el mensaje de error
            System.err.println("E/S: " + e.getMessage());
        }

    }
}
