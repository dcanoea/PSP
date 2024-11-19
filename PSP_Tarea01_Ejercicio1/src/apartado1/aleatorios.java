package apartado1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author David Cano Escario
 */
public class aleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Integer num;
        
        for (int i = 0; i < 40; i++) {
            num = (int)(Math.random()*100+1);
            System.out.println(num);
        }
    }
    
}
