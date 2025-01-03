/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Executor;

import java.util.Random;

/******************************************************************************
 *
 * @author IMCG
 */
public class NumerosAleatorios implements Runnable {

    /**************************************************************************
     * compone una cadena de diez números aleatorios menores que 50, separados
     * por ','
     */
    public void run() {

        String strReturn = "";
        Random random = new Random();

        for (int i = 0; i <10; i++) {
            strReturn += random.nextInt(50) + ", ";
            Thread.yield();
        }

        System.out.println("Numeros aleatorio obtenidos por "
                + Thread.currentThread().getName() + ": " + strReturn);
    }
}
