package psp_tarea04_ej01;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author David Cano Escario
 */
public class Cliente_TCP {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        try {
            // Crea el socket TCP para conectarse al servidor
            Socket socket = new Socket("localhost", 2000);

            // Crea un flujo de entrada para recibir mensajes del servidor
            InputStream input = socket.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);
            
            // Crea un flujo de salida para enviar mensajes al servidor
            OutputStream output = socket.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);
            System.out.println("Conectado al servidor. ¡Bienvenido al juego de adivinar el número!");

            // Variable para controlar si se ha adivinado el número
            boolean adivinado = false;

            while (!adivinado) {
                // Lee la respuesta del servidor
                String respuestaServidor = flujoEntrada.readUTF();
                System.out.println("Servidor dice: " + respuestaServidor);

                // Si el servidor indica que el número ha sido adivinado, termina el bucle
                if (respuestaServidor.equals("CORRECTO!")) {
                    adivinado = true;
                    break;
                }

                // Pide al usuario que introduzca un número
                System.out.print("Introduce un número del 0 al 100: ");
                String numero = s.nextLine();

                // Envía el número al servidor
                flujoSalida.writeUTF(numero);
            }

            System.out.println("¡Felicidades! Has adivinado el número.");

        } catch (UnknownHostException e) {
            // Captura el error si la máquina es desconocida
            System.err.println("Desconocido: " + e.getMessage());
        } catch (IOException e) {
            // Captura cualquier error de entrada/salida
            System.err.println("E/S: " + e.getMessage());
        }
    }
}
