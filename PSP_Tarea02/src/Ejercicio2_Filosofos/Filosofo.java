/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2_Filosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author David Cano Escario
 */
public class Filosofo extends Thread {

    private int id;
    private Semaphore palilloDerecho;
    private Semaphore palilloIzquierdo;
    private int cuentaComida;

    public Filosofo(int id, Semaphore palDer, Semaphore palIzq) {
        this.id = id;
        this.palilloDerecho = palDer;
        this.palilloIzquierdo = palIzq;
        this.cuentaComida = 0;
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            comer();
        }
    }

    private void pensar() {
        try {
            System.out.println("<< >> Filosofo " + id + " está pensando");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            System.out.println("Error al pensar");
            ex.printStackTrace();
        }
    }

    private void comer() {
        try {

            System.out.println("-- Filosofo " + id + " está hambriento");
            Thread.sleep((long) (Math.random() * 1000));

            if (cogerPalilloDerecho() == true && cogerPalilloIzquierdo() == true) {
                cuentaComida++;
                System.out.println("Filosofo " + id + " come. ---------- Ha comido " + cuentaComida + " veces. ----------------- ");
                dejarPalillos();
                Thread.sleep((long) (Math.random() * 1000));

            } else {
                System.out.println("------------ No hay palillos disponibles");
                dejarPalillos();
            }

        } catch (InterruptedException ex) {
            System.out.println("Error al comer");
            ex.printStackTrace();
        }
    }

    private boolean cogerPalilloDerecho() {
        try {

            palilloDerecho.acquire();
            System.out.println("|d Filosofo " + id + " coge el palillo derecho " + id);

            return true;
        } catch (InterruptedException ex) {
            System.out.println("Error al coger palillos");
            ex.printStackTrace();
        }

        return false;
    }

    private boolean cogerPalilloIzquierdo() {
        try {
            palilloIzquierdo.acquire();
            System.out.println("|i Filosofo " + id + " coge el palillo izquierdo " + ((id + 1) % 5));

            return true;
        } catch (InterruptedException ex) {
            System.out.println("Error al coger palillos");
            ex.printStackTrace();
        }

        return false;
    }

    private void dejarPalillos() {
        palilloDerecho.release();
        palilloIzquierdo.release();
        System.out.println("|| Filosofo " + id + " deja Libres palillos " + id + " y " + ((id + 1) % 5));
    }

}
