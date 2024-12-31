/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResumenThread_3_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class Hilo2 implements Runnable{

    private Thread miHilo = null;
    
    public Hilo2(String str) {
        miHilo = new Thread (this, str);
        miHilo.start();
    }
        
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + miHilo.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("FIN! " + miHilo.getName());
    }
    
}
