/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros_de_Registro;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author David Cano Escario
 */
public class MyLogger {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            // Configuro el logger y establezco el formato
            fh = new FileHandler("./src/main/java/Ficheros_de_Registro/MyLogFile.log", true);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // Añado un mensaje al log   
            logger.log(Level.WARNING, "Mi primer log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
