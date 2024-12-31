/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplos_Multitarea_Hilos_Supermercado;

/**
 *
 * @author David Cano Escario
 */
public class Cliente {
    private String nombre;
    private int[] carroCompra;

    public Cliente(String nombre, int[] carrroCompra) {
        this.nombre = nombre;
        this.carroCompra = carrroCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarroCompra(int[] carrroCompra) {
        this.carroCompra = carrroCompra;
    }
    
    
}
