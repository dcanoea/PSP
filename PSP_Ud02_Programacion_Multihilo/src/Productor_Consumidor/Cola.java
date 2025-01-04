/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productor_Consumidor;


/**
 *
 * @author David Cano Escario
 */
class Cola {

    private int [] datos;
    private int sigEnt, sigSal, ocupados, tamano;

    public Cola(int tam) {
        datos = new int[tam];
        tamano = tam;
        ocupados = 0;
        sigEnt = 1;
        sigSal = 1;
    }

    
    public synchronized void almacenar(int x) {
        try {
            while (ocupados == tamano) {
                wait();
            }
            datos[sigEnt] = x;
            sigEnt = (sigEnt + 1) % tamano;
            ocupados++;
            notify();
        } catch (InterruptedException e) {
        }
    }

    public synchronized int extraer() {
        int x = 0;
        try {
            while (ocupados == 0) {
                wait();
            }
            x = datos[sigSal];
            sigSal = (sigSal + 1) % tamano;
            ocupados--;
            notify();

        } catch (InterruptedException e) {
        }
        return x;
    }
}
