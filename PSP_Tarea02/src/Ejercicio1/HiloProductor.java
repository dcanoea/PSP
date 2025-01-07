/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class HiloProductor extends Thread {

    private Cola buffer;

    public HiloProductor(Cola c) {
        this.buffer = c;
    }

    @Override
    public void run() {
        final char caracterTope = 'Z';//Ultimo caracter disponible
        char caracter = 'A';//Primer caracter

        for (int i = 0; i < 15; i++) {
            //agrega el caracter
            buffer.almacenar(caracter);
            System.out.println("<>Depositado el carácter " + caracter + 
                    " en el buffer <><><><><><> nº de carácteres en buffer " + buffer.getOcupados());
            //incrementa el carácter (A, B, C...)
            caracter++;
            //si llega al tope (Z)
            if (caracter == caracterTope) {
                //empieza otra vez por 'A'
                caracter = 'A';
            }
        }

    }
}
