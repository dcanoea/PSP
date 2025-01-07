/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio2_Filosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author David Cano Escario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] palillos = new Semaphore[numFilosofos];

        //Inicia palillos con un solo permiso (solo puede estar en la mano de un filosofo
        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Semaphore(1);
        }

        //Crear hilos de los filosofos
        Thread[] filosofos = new Thread[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            Semaphore palilloDerecho = palillos[i];
            Semaphore palilloIzquierdo = palillos[(i + 1) % numFilosofos]; // (0+1) % 5 = 1 ----  (1+1) % 5 = 2 ---- (2+1) % 5 = 3 ---- (3+1) % 5 = 4 ---- (4+1) % 5 = 0 ----

            filosofos[i] = new Filosofo(i, palilloDerecho, palilloIzquierdo);

            filosofos[i].start();
        }
    }

}
