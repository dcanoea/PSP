/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posiblehiloegoista;

/**
 *
 * @author eagullof
 */
public class Color extends Thread {
   //clase que extiende a Thread
    String color;
    
    //constructor
    public Color (String c){
        color=c;
    }
   public void run(){
       //se imprime 100 veces el valor de: color + i
       for(int i=1;i<=100;i++)
            System.out.println(color + i);
            Thread.yield(); //llamada a yield()
    }
}
