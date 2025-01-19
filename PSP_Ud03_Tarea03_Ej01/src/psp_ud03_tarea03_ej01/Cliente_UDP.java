/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_ud03_tarea03_ej01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class Cliente_UDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        try {
            //Crea el socket UDP para enviar el mensaje
            DatagramSocket sSocket = new DatagramSocket();

            //Obtiene la dirección IP de la máquina a la que se enviará el mensaje
            InetAddress maquina = InetAddress.getByName("localhost");
            int puerto = 2000;

            //Con esta variable vamos a controlar si se ha adivinado o no el número secreto
            boolean adivinado = false;
            while (!adivinado) {
                System.out.println("Introduce un número del 0 al 100");
                String numero = s.nextLine();

                //Convierte el mensaje introducido mediante Scanner a un arreglo de bytes    
                byte[] cadena = numero.getBytes();

                //Crea el paquete de datos a enviar, con el mensaje, su longitud, la dirección de la máquina y el puerto
                DatagramPacket mensaje = new DatagramPacket(cadena, cadena.length, maquina, puerto);

                //Envía el paquete de datos al receptor
                sSocket.send(mensaje);
                
                //Buffer para recibir la respuesta del servidor
                byte[] cadenaRespuesta = new byte[128];//Un String ocupa 10 bytes + la longitud de la cadena
                DatagramPacket respuesta = new DatagramPacket(cadenaRespuesta, cadenaRespuesta.length);
                
                //Recibimos la respuesta del servidor
                sSocket.receive(respuesta);
                String respuestaServidor = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Servidor dice: " + respuestaServidor);
                
                //Comprobamos si se ha adivinado el numero
                if (respuestaServidor.equals("CORRECTO!")){
                    adivinado = true;
                }
            }
                        
            //Cierra el socket una vez adivinado el número
            sSocket.close();

        } catch (UnknownHostException e) {
            // Captura el error si la máquina es desconocida
            System.err.println("Desconocido: " + e.getMessage());
        } catch (SocketException e) {
            // Captura cualquier error relacionado con el socket
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            // Captura cualquier error de entrada/salida
            System.err.println("E/S: " + e.getMessage());
        }

    }
}
