/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;


/**
 *
 * @author David Cano Escario
 */
public class Cola {

    private char[] datos;
    private int entra, sale, ocupados, tamano;

    public Cola(int tamano) {
        this.datos = new char[tamano];
        this.entra = 0;  // Indica la siguiente posici�n para insertar un valor
        this.sale = 0;   // Indica la siguiente posici�n para extraer un valor
        this.ocupados = 0;  // N�mero de elementos actualmente en el b�fer
        this.tamano = tamano;
    }

    public int getTamano() {
        return tamano;
    }
    
    

    // M�todo para almacenar caracteres en el b�fer (comportamiento LIFO)
    public synchronized void almacenar(char caracter) {
        try {
            // Espera si el b�fer est� lleno
            while (ocupados == tamano) {
                wait();
            }

            // Insertamos el car�cter en la �ltima posici�n disponible (se mueve el �ndice `entra`)
            datos[entra] = caracter;
            entra = (entra + 1) % tamano; // Avanza el �ndice de inserci�n
            ocupados++; // Aumentamos el n�mero de elementos ocupados

            // Notificamos a los consumidores que hay un nuevo car�cter disponible
            notifyAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura el estado de interrupci�n
        }
    }

    // M�todo para extraer caracteres del b�fer (comportamiento LIFO)
    public synchronized char extraer() {
        char caracter = 0;
        try {
            // Espera si el b�fer est� vac�o
            while (ocupados == 0) {
                wait();
            }

            // Extraemos el �ltimo car�cter insertado (LIFO)
            int pos = (entra + tamano - 1) % tamano;  // �ltima posici�n ocupada
            caracter = datos[pos];
            datos[pos] = 0;  // Limpiamos la posici�n ocupada
            entra = pos; // Actualiza el �ndice de inserci�n
            ocupados--; // Disminuye el n�mero de elementos ocupados

            // Notificamos a los productores que hay espacio disponible
            notifyAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura el estado de interrupci�n
        }
        return caracter;
    }
}


