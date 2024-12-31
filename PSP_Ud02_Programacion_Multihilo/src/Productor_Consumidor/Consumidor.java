/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productor_Consumidor;

import Productor_Consumidor.Cola;

/**
 *
 * @author David Cano Escario
 */

//CLASE CONSUMIDOR, ACCEDE PARA EXTRAER
//Extrae datos (int) del buffer indefinidamente.

public class Consumidor extends Thread{

    private Cola buffer;

    public Consumidor(Cola c) {
        buffer = c;
    }
    
    public void run () {
        int dato = 0;
        while (true) {            
            dato = buffer.extraer();
            System.out.println("Consumidor " + getName() + " " + dato);
        }
    }
    
    
}
