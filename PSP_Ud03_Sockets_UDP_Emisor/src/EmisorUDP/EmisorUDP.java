/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EmisorUDP;

/**
 *
 * @author David Cano Escario
 */
import java.net.*;
import java.io.*;

public class EmisorUDP {
    public static void main(String args[]) {
        // Comprueba que se pasen exactamente 2 argumentos: una m�quina y un mensaje
        if (args.length != 2) {
            System.err.println("Uso: java EmisorUDP maquina mensaje");
        } else try {
            // Crea el socket UDP para enviar el mensaje
            DatagramSocket sSocket = new DatagramSocket();

            // Obtiene la direcci�n IP de la m�quina a la que se enviar� el mensaje
            InetAddress maquina = InetAddress.getByName(args[0]);
            int Puerto = 1500;  // Establece el puerto 1500 para la transmisi�n

            // Convierte el mensaje a un arreglo de bytes
            byte[] cadena = args[1].getBytes();

            // Crea el paquete de datos a enviar, con el mensaje, su longitud, la direcci�n de la m�quina y el puerto
            DatagramPacket mensaje = new DatagramPacket(cadena, args[1].length(), maquina, Puerto);

            // Env�a el paquete de datos al receptor
            sSocket.send(mensaje);

            // Cierra el socket una vez enviado el mensaje
            sSocket.close();
        } catch (UnknownHostException e) {
            // Captura el error si la m�quina es desconocida
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
