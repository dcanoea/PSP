/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Cliente;

/**
 *
 * @author David Cano Escario
 */
import java.io.*;   
import java.net.*;  

class Cliente {
    static final String HOST = "localhost";  // Direcci�n IP del servidor, en este caso se conecta a localhost (m�quina local)
    static final int Puerto = 2000;         // Puerto al que se conecta el cliente

    public Cliente() {  
        try {
            // Se establece una conexi�n al servidor en el puerto 2000 de la m�quina local
            Socket sCliente = new Socket(HOST, Puerto);

            // Se obtiene un flujo de entrada para leer datos del servidor
            InputStream aux = sCliente.getInputStream(); 
            DataInputStream flujo_entrada = new DataInputStream(aux);

            // Se imprime el mensaje recibido del servidor (en este caso, el servidor enviar� un mensaje de saludo)
            System.out.println(flujo_entrada.readUTF());

            // Se cierra la conexi�n con el servidor
            sCliente.close();
        } catch (Exception e) {
            // Si ocurre alg�n error, se imprime el mensaje de error
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Cliente();  // Crear una nueva instancia de la clase Cliente para que se ejecute el programa
    }
}