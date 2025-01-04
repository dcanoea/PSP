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
        this.entra = 0;  // Indica la siguiente posición para insertar un valor
        this.sale = 0;   // Indica la siguiente posición para extraer un valor
        this.ocupados = 0;  // Número de elementos actualmente en el búfer
        this.tamano = tamano;
    }

    public int getTamano() {
        return tamano;
    }
    
    

    // Método para almacenar caracteres en el búfer (comportamiento LIFO)
    public synchronized void almacenar(char caracter) {
        try {
            // Espera si el búfer está lleno
            while (ocupados == tamano) {
                wait();
            }

            // Insertamos el carácter en la última posición disponible (se mueve el índice `entra`)
            datos[entra] = caracter;
            entra = (entra + 1) % tamano; // Avanza el índice de inserción
            ocupados++; // Aumentamos el número de elementos ocupados

            // Notificamos a los consumidores que hay un nuevo carácter disponible
            notifyAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura el estado de interrupción
        }
    }

    // Método para extraer caracteres del búfer (comportamiento LIFO)
    public synchronized char extraer() {
        char caracter = 0;
        try {
            // Espera si el búfer está vacío
            while (ocupados == 0) {
                wait();
            }

            // Extraemos el último carácter insertado (LIFO)
            int pos = (entra + tamano - 1) % tamano;  // Última posición ocupada
            caracter = datos[pos];
            datos[pos] = 0;  // Limpiamos la posición ocupada
            entra = pos; // Actualiza el índice de inserción
            ocupados--; // Disminuye el número de elementos ocupados

            // Notificamos a los productores que hay espacio disponible
            notifyAll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura el estado de interrupción
        }
        return caracter;
    }
}


