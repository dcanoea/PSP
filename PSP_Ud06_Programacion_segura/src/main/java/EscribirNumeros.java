
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author David Cano Escario
 */
public class EscribirNumeros {

    /**
     * @param args the command line arguments
     */
    private Vector numeros;
    private static final int SIZE = 100;

    public EscribirNumeros() {
        // Generamos el vector con números aleatorios
        numeros = new Vector(SIZE);
        Random randomGenerator = new Random();

        for (int i = 0; i < SIZE; i++) {
            Integer num = randomGenerator.nextInt(100);
            numeros.addElement(Integer.valueOf(num));
        }

        // Guardamos el vector en un fichero
        PrintWriter out = null;

        try {
            System.out.println("Entrando a la zona Try");
            out = new PrintWriter(new FileWriter("./src/main/java/Salida.txt"));

            for (int i = 0; i < SIZE; i++) {
                out.println("Valor de: " + i + " = " + numeros.elementAt(i));
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Cerrando el fichero");
                out.close();
            } else {
                System.out.println("NO se ha abierto el fichero");
            }
        }
    }

    public static void main(String[] args) {
        new EscribirNumeros();
    }

}
