/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package HiloRunnable;

/**
 *
 * @author David Cano Escario
 */
public class SaludoRunnable implements Runnable {

    //Redefinición del metodo run(9 con las instrucciones del hilo
    @Override
    public void run() {
        System.out.println("Hola! Este es el mensaje del hilo");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crear un objeto de la clase SaludoRunnable
        SaludoRunnable objetoSaludoRunnable = new SaludoRunnable();
        
        //Convertimos el objeto en un hilo
        Thread hilo1 = new Thread(objetoSaludoRunnable);
        
        //Iniciamos el hilo con el método start()
        hilo1.start();
    }

}
