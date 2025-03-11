package psp_tarea04_ej01;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 *
 * @author David Cano Escario
 */
public class Servidor_TCP {

    private static int contadorClientes = 0; // Contador para asignar IDs �nicos a los clientes

    public static void main(String[] args) {
        int numSecreto = new Random().nextInt(100) + 1; // N�mero aleatorio entre 1 y 100
        System.out.println("N�mero secreto generado: " + numSecreto);
        System.out.println("Adivina el n�mero del 1 al 100");

        try {
            // Crea un ServerSocket en el puerto 2000 para aceptar conexiones de clientes
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Servidor TCP iniciado. Esperando clientes...");

            //Bucle infinito para recibir conexiones de distintos clientes
            while (true) {
                // Acepta una conexi�n de un cliente
                Socket socketCliente = serverSocket.accept();
                contadorClientes++; // Incrementa el contador de clientes
                System.out.println("Cliente " + contadorClientes + " conectado: ");

                // Crea un hilo para manejar la interacci�n con el cliente
                Thread hilo = new Thread(new ManejadorCliente(socketCliente, numSecreto, contadorClientes));
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

class ManejadorCliente extends Thread {

    private Socket socketCliente;
    private int numSecreto;
    private int idCliente; // Identificador �nico del cliente

    public ManejadorCliente(Socket socketCliente, int numSecreto, int idCliente) {
        this.socketCliente = socketCliente;
        this.numSecreto = numSecreto;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            // Flujo de salida al cliente
            InputStream input = socketCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);

            //Flujo de entrada para leer los datos del cliente
            OutputStream output = socketCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);
            flujoSalida.writeUTF("�Bienvenido al juego de adivinar el n�mero! Introduce un n�mero entre 1 y 100.");

            int numRecibido;
            while (true) {
                // Lee el n�mero enviado por el cliente
                String datos = flujoEntrada.readUTF();
                numRecibido = Integer.parseInt(datos);

                // Muestra el n�mero recibido junto con el ID del cliente
                System.out.println("Cliente " + idCliente + " envi�: " + numRecibido);

                // Compara el n�mero recibido con el n�mero secreto
                String respuesta;
                if (numRecibido < numSecreto) {
                    respuesta = "El n�mero secreto es mayor";
                } else if (numRecibido > numSecreto) {
                    respuesta = "El n�mero secreto es menor";
                } else {
                    respuesta = "CORRECTO!";
                    flujoSalida.writeUTF(respuesta);
                    break;
                }

                // Env�a la respuesta al cliente
                flujoSalida.writeUTF(respuesta);
            }

            // Muestra un mensaje cuando el cliente adivina el n�mero
            System.out.println("Cliente " + idCliente + " ha adivinado el n�mero.");
            
            //Cerramos los flujos y socket
            flujoEntrada.close();
            flujoSalida.close();
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error con el cliente " + idCliente + ": " + e.getMessage());
        } 
    }
}
