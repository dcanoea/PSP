/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Productor_Consumidor;

/**
 *
 * @author David Cano Escario
 */



//EL PROGRAMA que maneja el buffer y las tareas
public class programaConHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola buffer;    //buffer monitor
        Productor p;            //1 Tarea Productor
        Consumidor [] c;        //Varias Tareas Consumidor
        
        buffer = new Cola(5);
        p = new Productor(buffer);
        c = new Consumidor[3];
        for (int i = 0; i < c.length; i++) {
            c[i] = new Consumidor(buffer);
        }
        
        p.start();
        for (int i = 0; i < c.length; i++) {
            c[i].start();
        }
    }
    
}
