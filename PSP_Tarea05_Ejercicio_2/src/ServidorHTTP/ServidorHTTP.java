package ServidorHTTP;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * *****************************************************************************
 * Servidor HTTP que atiende peticiones de tipo 'GET' recibidas por el puerto
 * 8066
 *
 * NOTA: para probar este código, comprueba primero de que no tienes ningún
 * otro servicio por el puerto 8066 (por ejemplo, con el comando 'netstat' si
 * estás utilizando Windows)
 *
 * @author David Cano Escario
 */
class ServidorHTTP {

    /**
     * **************************************************************************
     * procedimiento principal que asigna a cada petición entrante un socket
     * cliente, por donde se enviará la respuesta una vez procesada
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        Socket socketCliente;

        try {
            //Asociamos al servidor el puerto 8066
            ServerSocket socServidor = new ServerSocket(8066);
            imprimeDisponible();

            while (true) {
                //acepta una petici�n y le asigna un socket cliente para la respuesta
                socketCliente = socServidor.accept();
                System.out.println("Cliente conectado.");
                //Crea un nuevo hilo para despacharla por el socketCliente que le asign�
                Thread hilo = new HiloDespachador(socketCliente);
                hilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * **************************************************************************
     * muestra un mensaje en la Salida que confirma el arranque, y da algunas
     * indicaciones posteriores
     */
    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
                + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
                + "localhost:8066/q\n para simular un error");
    }

    private static class HiloDespachador extends Thread {

        private Socket socketCliente;

        public HiloDespachador(Socket socketCliente) {
            this.socketCliente = socketCliente;
        }

        public void run() {

            InputStreamReader inSR = null;
            try {
                //variables locales
                String peticion;
                String html;
                //Flujo de entrada
                inSR = new InputStreamReader(
                        socketCliente.getInputStream());
                //espacio en memoria para la entrada de peticiones
                BufferedReader bufLeer = new BufferedReader(inSR);
                //objeto de java.io que entre otras características, permite escribir
                //'línea a línea' en un flujo de salida
                PrintWriter printWriter = new PrintWriter(
                        socketCliente.getOutputStream(), true);
                //mensaje petición cliente
                peticion = bufLeer.readLine();
                //para compactar la petición y facilitar así su análisis, suprimimos todos
                //los espacios en blanco que contenga
                peticion = peticion.replaceAll(" ", "");
                //si realmente se trata de una petición 'GET' (que es la única que vamos a
                //implementar en nuestro Servidor)
                if (peticion.startsWith("GET")) {
                    //extrae la subcadena entre 'GET' y 'HTTP/1.1'
                    peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

                    System.out.println("Peticion Cliente : " + peticion);

                    //si corresponde a la página de inicio
                    if (peticion.length() == 0 || peticion.equals("/")) {
                        //sirve la página
                        html = Paginas.html_index;
                        printWriter.println(Mensajes.lineaInicial_OK);
                        printWriter.println(Paginas.primeraCabecera);
                        printWriter.println(Paginas.segundaCabecera);
                        printWriter.println(Paginas.cabeceraDate);
                        printWriter.println("Content-Length: " + html.length());
                        printWriter.println("\n");
                        printWriter.println(html);
                    } //si corresponde a la página del Quijote
                    else if (peticion.equals("/quijote")) {
                        //sirve la página
                        html = Paginas.html_quijote;
                        printWriter.println(Mensajes.lineaInicial_OK);
                        printWriter.println(Paginas.primeraCabecera);
                        printWriter.println(Paginas.segundaCabecera);
                        printWriter.println(Paginas.cabeceraDate);
                        printWriter.println("Content-Length: " + html.length());
                        printWriter.println("\n");
                        printWriter.println(html);
                    } //en cualquier otro caso
                    else {
                        //sirve la página
                        html = Paginas.html_noEncontrado;
                        printWriter.println(Mensajes.lineaInicial_NotFound);
                        printWriter.println(Paginas.primeraCabecera);
                        printWriter.println(Paginas.segundaCabecera);
                        printWriter.println(Paginas.cabeceraDate);
                        printWriter.println("Content-Length: " + html.length());
                        printWriter.println("\n");
                        printWriter.println(html);
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socketCliente.close();
                    inSR.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
