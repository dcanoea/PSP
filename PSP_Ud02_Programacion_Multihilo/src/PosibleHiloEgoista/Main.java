/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package posiblehiloegoista;

/**
 *
 * @author eagullof
 */
public class Main {
 public static void main(String[] args) {
       //se crean dos hilos: hrojo y hazul
       Color hrojo = new Color ("Rojo");
       Color hazul = new Color ("Azul");
       //se inician los hilos para su ejecución
       hrojo.start();
       hazul.start();
    }
}
