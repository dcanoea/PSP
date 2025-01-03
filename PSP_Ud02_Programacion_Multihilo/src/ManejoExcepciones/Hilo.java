/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoExcepciones;

/**
 *
 * @author IMCG
 */
import java.util.Random;

public class Hilo extends Thread {
//clase que implementa el hilo
    
    //constructor
    public Hilo(String nombre) {
        super(nombre);
    }

    //
    public void run() {
        Random numero = new Random();
        //crea un objeto Random
        int num = numero.nextInt(4);
        int division = 100 / num;
        //divide 100 entre el número pseudoaleatorio entre 0 y 4
        System.out.println(getName() +" Division 100 / " + num + ": " + division);
        //muestra el valor de la división
    }
}
