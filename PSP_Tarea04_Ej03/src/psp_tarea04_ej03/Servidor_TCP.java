/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_tarea04_ej03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David Cano Escario
 */
public class Servidor_TCP {

    static int contadorClientes = 0;
    static int puerto = 2000;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en puerto " + puerto);

            while (true) {
                Socket socketCliente = serverSocket.accept();
                contadorClientes++;
                System.out.println("Cliente " + contadorClientes + " conectado");

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
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());

            String user = flujoEntrada.readUTF();
            String pass = flujoEntrada.readUTF();

            if (user.equals("David") && pass.equals("Cano")) {
                flujoSalida.writeUTF("Autenticacion OK");

                String comando;
                boolean continuar = true;

                while (continuar) {
                    flujoSalida.writeUTF("Introduce comando (ls/get/exit)");
                    comando = flujoEntrada.readUTF();
                    
                    switch (comando) {
                        case "ls":
                            flujoSalida.writeUTF("Introduce el directorio a listar:");
                            String directorioPath = flujoEntrada.readUTF();
                            File directorio = new File(directorioPath);

                            if (directorio.exists() && directorio.isDirectory()) {
                                String[] archivos = directorio.list();
                                for (String archivo : archivos) {
                                    flujoSalida.writeUTF(archivo);
                                }
                            } else {
                                flujoSalida.writeUTF("El directorio no existe o no es válido.");
                            }
                            flujoSalida.writeUTF("EOF");
                            break;

                        case "get":
                            flujoSalida.writeUTF("Introduce el nombre del fichero: ");
                            String fichero = flujoEntrada.readUTF();
                            File file = new File(fichero);

                            if (file.exists()) {
                                flujoSalida.writeUTF("Encontrado");
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String linea;
                                while ((linea = br.readLine()) != null) {
                                    flujoSalida.writeUTF(linea);
                                }
                                flujoSalida.writeUTF("EOF");
                                br.close();
                            } else {
                                flujoSalida.writeUTF("El fichero no existe");
                            }
                            break;

                        case "exit":
                            System.out.println("Cliente " + idCliente + " desconectado.");
                            flujoSalida.writeUTF("Desconectado");
                            continuar = false;
                            break;

                        default:
                            flujoSalida.writeUTF("Comando no reconocido");
                            break;
                    }
                }

                flujoSalida.close();
                flujoEntrada.close();
                socketCliente.close();

            } else {
                flujoSalida.writeUTF("Error en la autenticación");
                socketCliente.close();
            }

        } catch (IOException ex) {
            System.err.println("Error con el cliente " + idCliente + ": " + ex.getMessage());
        }
    }
}
