/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2_Filosofos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        while (cuentaComida <= 10) {
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
            
            if (cogerPalillos() == true){
            cuentaComida++;
            System.out.println("Filosofo " + id + " come. ---------- Ha comido " + cuentaComida + " veces. -----------------");
            Thread.sleep((long) (Math.random() * 1000));}
            
            
            dejarPalillos();
            
        } catch (InterruptedException ex) {
            System.out.println("Error al comer");
            ex.printStackTrace();
        }
    }

    private boolean cogerPalillos() {
        try {
            palilloDerecho.acquire();
            System.out.println("|d Filosofo " + id + " coge el palillo derecho " + id);

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
        System.out.println("Filosofo " + id + " deja palillos");
    }

}
