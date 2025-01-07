/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio1;

/**
 *
 * @author David Cano Escario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Cola buffer = new Cola(6);

        HiloProductor productor = new HiloProductor(buffer);
        HiloConsumidor consumidor = new HiloConsumidor(buffer);

        productor.start();

        consumidor.start();

    }

}
