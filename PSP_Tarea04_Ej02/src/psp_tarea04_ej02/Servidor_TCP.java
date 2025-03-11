/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_tarea04_ej02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David Cano Escario
 */
public class Servidor_TCP {

    static int contadorClientes = 0; //Contador para asignar Id a los clientes
    static int puerto = 2000;

    public static void main(String[] args) {
        try {
            // Creamos el objeto ServerSocket que escuchará en el puerto 2000
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en puerto " + puerto);

            //Bucle infinito para recibir conexiones de distintos clientes
            while (true) {
                //Esperamos una conexión entrante del cliente
                Socket socketCliente = serverSocket.accept();
                contadorClientes++;//Incrementa el contador de clientes
                System.out.println("Cliente " + contadorClientes + " conectado");

                //Crea un hilo para manejar al cliente
                Thread hilo = new Thread(new ManejadorCliente(socketCliente, contadorClientes));
                hilo.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

class ManejadorCliente extends Thread {

    private Socket socketCliente;
    private int idCliente;

    public ManejadorCliente(Socket socketCliente, int idCliente) {
        this.socketCliente = socketCliente;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            //Flujo de entrada desde el cliente
            InputStream input = socketCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);

            //Flujo de salida hacia el cliente
            OutputStream output = socketCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);

            //Leer el nombre del fichero solicitado
            String fichero = flujoEntrada.readUTF();
            File file = new File(fichero);
            if (file.exists()) {
                flujoSalida.writeUTF("Encontrado");//Respuesta que se enviará al cliente
                System.out.println("Cliente " + idCliente + ": El fichero existe");

                //Enviamos el contenido del fichero
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    flujoSalida.writeUTF(linea);//Se envía cada línea del archivo al cliente hasta que no haya más. Se usa el flujo de salida
                }

                flujoSalida.writeUTF("EOF");  // Marcador para indicar el fin del archivo
                System.out.println("Fichero enviado al cliente " + idCliente);
                br.close();
                fr.close();
            } else {
                flujoSalida.writeUTF("Cliente " + idCliente + ": El fichero no existe");//Respuesta que se enviará al cliente
                System.out.println("Cliente " + idCliente + ": El fichero no existe");
            }

            socketCliente.close();
        } catch (IOException ex) {
            System.err.println("error con el cliente " + idCliente + ": " + ex.getMessage());
        }
    }
}
