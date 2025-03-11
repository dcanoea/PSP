/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_tarea03_ej02;

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

            //Esperamos una conexión entrante del cliente
            Socket sCliente = skServidor.accept();
            System.out.println("Cliente conectado");

            //Flujo de entrada desde el cliente
            InputStream input = sCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(input);

            //Flujo de salida hacia el cliente
            OutputStream output = sCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(output);

            //Leer el nombre del fichero solicitado
            String fichero = flujoEntrada.readUTF();
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
            
            sCliente.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
