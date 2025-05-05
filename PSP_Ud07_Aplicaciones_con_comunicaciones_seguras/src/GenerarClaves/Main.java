/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generarclaves;
//bibliotecas necesarias

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author IMCG
 */
public class Main {

    //Programa que crea una pareja de claves (p�blica y privada) y las muestra
    public static void main(String[] args) {
        //Asigna al objeto claves de tipo keyPair el par de claves generadas
        //por el m�todo GeneraParejaClave()
        KeyPair claves = GeneraParejaClave();
        //Imprime el valor de las claves generadas en diferentes formatos
        System.out.println("Algoritmo Kprivada: "
                + claves.getPrivate().getAlgorithm());
        System.out.println("Codificaci�n Kprivada: "
                + claves.getPrivate().getFormat());
        System.out.println("Bytes Kprivada: "
                + claves.getPrivate().toString());
        System.out.println("Algoritmo Kp�blica: "
                + claves.getPublic().getAlgorithm());
        System.out.println("Codificaci�n Kp�blica: "
                + claves.getPublic().getFormat());
        System.out.println("Bytes Kp�blica: "
                + claves.getPublic().toString());

        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            String texto = "Texto para el mensaje ejemplo SHA1";
            sha1.update(texto.getBytes()); //obtiene el resumen
            byte[] resumen = sha1.digest(); //completa la generaci�n del resumen
            for (int k = 0; k < resumen.length; k++) { //muestra el resumen
                System.out.println("(" + resumen[k] + ")");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //M�todo que genera una clave tipo KeyPair (uan pareja de claves)
    public static KeyPair GeneraParejaClave() {
        KeyPair claves = null;
        try {
            //Crea el objeto para generar un par de claves mediante RSA
            KeyPairGenerator genera = KeyPairGenerator.getInstance("RSA");
            genera.initialize(512); //asigna tama�o de la clave
            claves = genera.generateKeyPair(); //genera la pareja de claves
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claves;
    }

}
