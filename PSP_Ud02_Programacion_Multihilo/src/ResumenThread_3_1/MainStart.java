/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ResumenThread_3_1;

/**
 *
 * @author David Cano Escario
 */
public class MainStart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hilo uno, dos;
        
        uno = new Hilo("Jamaica");
        dos = new Hilo("Fiji");
        
        uno.start();
        dos.start();
        
        System.out.println("main no hace nada");
    }
    
}
