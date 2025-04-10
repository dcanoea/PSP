/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Validaciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David Cano Escario
 */
public class Ejemplo_II {

    /**
     * @param args the command line arguments
     */
   public Ejemplo_II(){
          String dni_cliente=new String();          
          Pattern pat=null;
          Matcher mat=null;

          // para leer del teclado
          BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

          try{
               System.out.println("Introduce tu DNI (Formato 00000000-A):");
               dni_cliente=reader.readLine();
          
               pat=Pattern.compile("[0-9]{8}-[a-zA-Z]");
               mat=pat.matcher(dni_cliente);
               
               if(mat.find()){
                         System.out.println("Correcto!!  "+dni_cliente);
               }else{
                    System.out.println("El DNI esta mal  "+dni_cliente);
               }
               
          } catch( Exception e ) {
               System.out.println( e.getMessage() );
          }
     }

     public static void main( String[] arg ) {
          new Ejemplo_II();
     }

}