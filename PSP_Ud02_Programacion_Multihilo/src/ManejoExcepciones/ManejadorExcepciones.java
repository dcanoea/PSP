/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ManejoExcepciones;

/**
 *
 * @author IMCG
 */
public class ManejadorExcepciones implements Thread.UncaughtExceptionHandler{//manejador de excepciones para toda la aplicación
    
//implementa el método uncaughtException()
    public void uncaughtException(Thread t, Throwable e){
        System.out.printf("Thread que lanz� la excepci�n: %s \n", t.getName());
        //muestra en consola el hilo que produce la exceción
        e.printStackTrace();
        //muestra en consola la pila de llamadas
    }
}
