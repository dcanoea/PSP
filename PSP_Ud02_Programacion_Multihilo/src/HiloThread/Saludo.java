/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package HiloThread;

/**
 *
 * @author David Cano Escario
 */
public class Saludo extends Thread{

    //Redefinición del método run() con las instrucciones del hilo
    @Override
    public void run(){
        System.out.println("Hola este es el mensaje del hilo");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creación del objeto hilo1 de la clase Saludo (el hilo)
        Saludo hilo1 = new Saludo();
        
        //Iniciar el hilo invocando el método start()
        hilo1.start();
    }
    
}
