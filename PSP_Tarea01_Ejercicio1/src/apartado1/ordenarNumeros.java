package apartado1;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author David Cano Escario
 */
public class ordenarNumeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer num;
        ArrayList<Integer> lista = new ArrayList<Integer>();

        Scanner s = new Scanner(System.in);
        System.out.println("Introduce numeros, finaliza al introducir otro tipo de carácter");
        while (s.hasNextInt()) {
            num = s.nextInt();
            lista.add(num);
        }

        Collections.sort(lista);

        System.out.println("Números ordenados");
        System.out.println(lista);

    }

}
