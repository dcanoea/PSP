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
public class HiloConsumidor extends Thread {

    private Cola buffer;

    public HiloConsumidor(Cola c) {
        this.buffer = c;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(300);
                System.out.println("Recogido el carácter " + buffer.extraer() + " del buffer");
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloConsumidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
