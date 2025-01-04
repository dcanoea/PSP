/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

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
    public void run() {
        try {
            
            for (int i = 0; i < 15; i++) {
                Thread.sleep(300);
                System.out.println("Recogido el carácter " + buffer.extraer() + " del buffer");
            }

        } catch (Exception e) {
        }
    }
}
