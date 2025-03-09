/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejemplo3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author David Cano Escario
 */
public class Servidor3 {

    static final int PUERTO = 2000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket sCliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");
                new ClienteHandler(sCliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClienteHandler extends Thread {

    private Socket socket;
    private int estado = 0;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream())) {
            String comando;
            flujoSalida.writeUTF("Introduce el comando: ls, get [archivo], exit");

            while (estado != -1) {
                comando = flujoEntrada.readLine();

                switch (estado) {
                    case 0 -> {
                        flujoSalida.writeUTF("Introduce el comando: ls, get [archivo], exit");
                        estado = 1; // Pasa al estado de espera de comando
                    }
                    case 1 -> {
                        if (comando.equals("ls")) {
                            listarDirectorio(flujoSalida);
                            estado = 1; // Se mantiene en espera de comandos
                        } else if (comando.startsWith("get ")) {
                            estado = 3;
                            flujoSalida.writeUTF("Introduce el nombre del archivo:");
                        } else if (comando.equals("exit")) {
                            estado = -1;
                        } else {
                            flujoSalida.writeUTF("Comando no valido. Intenta de nuevo.");
                        }
                    }
                    case 3 -> {
                        mostrarArchivo(comando, flujoSalida);
                        estado = 1;
                    }
                }
            }

            flujoSalida.writeUTF("Conexion terminada.");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listarDirectorio(DataOutputStream flujoSalida) throws IOException {
        File dir = new File(".");
        StringBuilder listado = new StringBuilder("Archivos en el directorio:\n");

        for (File file : dir.listFiles()) {
            listado.append(file.getName()).append("\n");
        }

        flujoSalida.writeUTF(listado.toString());
    }

    private void mostrarArchivo(String nombreArchivo, DataOutputStream flujoSalida) throws IOException {
        Path path = Paths.get(nombreArchivo);

        if (Files.exists(path)) {
            String contenido = Files.readString(path);
            flujoSalida.writeUTF("Contenido de " + nombreArchivo + ":\n" + contenido);
        } else {
            flujoSalida.writeUTF("El archivo no existe.");
        }
    }
}
