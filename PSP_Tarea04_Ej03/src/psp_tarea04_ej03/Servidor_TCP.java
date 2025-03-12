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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author David Cano Escario
 */
public class Servidor_TCP {

    /**
     * @param args the command line arguments
     */
    static final int puerto = 2000;

    public static void main(String[] args) {
        try {
            // Creamos el objeto ServerSocket que escuchará en el puerto 2000
            ServerSocket skServidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en puerto " + puerto);

            //Permitimos la conexión de varios clientes con un bucle infinito
            while (true) {
                //Esperamos una conexión entrante del cliente
                Socket sCliente = skServidor.accept();
                System.out.println("Cliente conectado");

                //Flujo de entrada desde el cliente
                InputStream input = sCliente.getInputStream();
                DataInputStream flujoEntrada = new DataInputStream(input);

                //Flujo de salida hacia el cliente
                OutputStream output = sCliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(output);

                int estado = 1;
                String comando = "";
                do {
                    switch (estado) {
                        case 1:
                            flujoSalida.writeUTF("Introduce comando (ls/get/exit)");
                            comando = flujoEntrada.readUTF();

                            if (comando.equals("ls")) {
                                System.out.println("El cliente quiere ver el contenido del directorio");
                                String directorioEntrada = flujoEntrada.readUTF();
                                File directorio = new File(directorioEntrada);

                                if (directorio.exists() && directorio.isDirectory()) {
                                    String listaDirectorios[] = directorio.list();
                                    for (String l : listaDirectorios) {
                                        flujoSalida.writeUTF(l);
                                    }
                                    flujoSalida.writeUTF("EOF");
                                } else {
                                    flujoSalida.writeUTF("El directorio no existe");
                                }
                                estado = 1;
                                break;
                            }
                            if (comando.equals("get")) {
                                //Leer el nombre del fichero solicitado
                                System.out.println("El cliente quiere ver el contenido del fichero solicitado");
                                estado = 3;
                                break;
                            }
                            break;

                        case 3://voy a mostrar el archivo
                            flujoSalida.writeUTF("Introduce el nombre del archivo");
                            String fichero = flujoEntrada.readUTF();
                            // Muestro el fichero
                            File file = new File(fichero);
                            if (file.exists()) {
                                flujoSalida.writeUTF("Encontrado");//Respuesta que se enviará al cliente
                                System.out.println("Fichero existe");

                                //Enviamos el contenido del fichero
                                FileReader fr = new FileReader(file);
                                BufferedReader br = new BufferedReader(fr);
                                String linea;
                                while ((linea = br.readLine()) != null) {
                                    flujoSalida.writeUTF(linea);//Se envía cada línea del archivo al cliente hasta que no haya más. Se usa el flujo de salida
                                }

                                flujoSalida.writeUTF("EOF");  // Marcador para indicar el fin del archivo
                                System.out.println("Fichero enviado al cliente");
                                br.close();
                                fr.close();
                            } else {
                                flujoSalida.writeUTF("El fichero no existe");//Respuesta que se enviará al cliente
                                System.out.println("Fichero no existe");
                            }

                            estado = 1;
                            break;
                    }

                    if (comando.equals("exit")) {
                        estado = -1;
                    }
                } while (estado != -1);

                sCliente.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
