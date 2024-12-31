/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productor_Consumidor;

import Productor_Consumidor.Cola;

/**
 *
 * @author David Cano Escario
 */
//CLASE PRODUCTOR, ACCEDE PARA ALMACENAR
//Genera datos (int) y los almacena en el buffer indefrinidamente
public class Productor extends Thread {

    private Cola buffer;

    public Productor(Cola c) {
        buffer = c;
    }

    public void run() {
        int valor = 0;
        while (true) {

            System.out.println("Productor " + valor);
            buffer.almacenar(valor);
            valor++;
        }
    }
}
