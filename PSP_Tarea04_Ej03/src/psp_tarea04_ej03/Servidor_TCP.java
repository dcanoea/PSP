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
                int estado = 1;
                do {

                    switch (estado) {

                        case 1:
                            flujoSalida.writeUTF("Introduce comando (ls/get/exit)");
                            comando = flujoEntrada.readUTF();

                            if (comando.equals("ls")) {
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
                                estado = 1;
                                break;
                            } else if (comando.equals("get")) {
                                estado = 3;
                                break;
                            } else if (comando.equals("exit")) {
                                estado = -1;
                                break;
                            } else {
                                flujoSalida.writeUTF("Comando no reconocido");
                                estado = 1;
                                break;
                            }

                        case 3:
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
                            estado = 1;
                            break;

                        case -1:
                            System.out.println("Cliente " + idCliente + " desconectado.");
                            flujoSalida.writeUTF("Desconectado");
                            estado = -1;
                            break;

                        default:
                            flujoSalida.writeUTF("Comando no reconocido");
                            estado = 1;
                    }
                } while (estado != -1);

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
