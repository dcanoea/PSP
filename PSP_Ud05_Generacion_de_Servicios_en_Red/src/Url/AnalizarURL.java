/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Url;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author David Cano Escario
 */
public class AnalizarURL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Constructor
            URL direccion = new URL("http://ftp.rediris.es/debian/README.mirrors.txt");
            System.out.println(direccion);

            //Divide las diferentes partes de una URL
            System.out.println("El protocolo utilizado es: " + direccion.getProtocol());
            System.out.println("El host es: " + direccion.getHost());
            System.out.println("El puerto es: " + direccion.getPort());
            System.out.println("El puerto por defecto asociado a la URL es: " + direccion.getDefaultPort());
            System.out.println("El fichero es: " + direccion.getFile());
            System.out.println("La referencia de la URL es: " + direccion.getRef());

        } catch (MalformedURLException ex) {
            System.out.println("Error en la construcción de la URL");
        }
    }

}
