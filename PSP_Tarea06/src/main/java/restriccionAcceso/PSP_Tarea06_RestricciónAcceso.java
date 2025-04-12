/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package restriccionAcceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David Cano Escario
 */
public class PSP_Tarea06_RestricciónAcceso {

    public static void main(String[] args) {
        try {
            //Creamos el logger
            Logger logger = Logger.getLogger("MyLog");
            //Una vez definido el logger debemos asociarlo a un fichero log:
            FileHandler fh = new FileHandler("./src/main/java/proyecto/MyLogFile.log", true);
            logger.addHandler(fh);
            //Si no queremos ver los mensajes de log por pantalla 
            logger.setUseParentHandlers(false);
            //Establecemos el nivel de registros a "TODOS"
            logger.setLevel(Level.ALL);
            //Establecer el formato del fichero en texto simple
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.log(Level.FINEST, "Inicio del ejercicio");

            Scanner s = new Scanner(System.in);
            logger.log(Level.FINEST, "Clase Scanner creada");

            System.out.println("Introduce tu usuario: (formato aaaaaaaa)");
            logger.log(Level.FINEST, "Introducción del USUARIO por teclado");
            String user = s.nextLine();

            if (validarUsuario(user)) {
                logger.log(Level.FINEST, "USUARIO VALIDO");
                System.out.println("Usuario ES VALIDO");

                System.out.println("Introduce el fichero a buscar (formato aaaaaaaa.aaa)");
                logger.log(Level.FINEST, "Introducción del FICHERO por teclado");
                String fichero = s.nextLine();
                if (validarFichero(fichero)) {
                    logger.log(Level.FINEST, "ARCHIVO VALIDO");

                    System.out.println("Fichero ES VALIDO");

                    BufferedReader br = null;
                    try {
                        File archivo = new File("./src/main/java/proyecto/" + fichero);
                        if (archivo.isFile()) {
                            logger.log(Level.FINEST, "ARCHIVO EXISTE");

                            System.out.println("Contenido del fichero");
                            br = new BufferedReader(new FileReader(archivo));
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                System.out.println(linea);
                            }
                            logger.log(Level.FINEST, "ARCHIVO MOSTRADO POR PANTALLA");

                        }
                    } catch (FileNotFoundException ex) {
                        logger.log(Level.SEVERE, "ARCHIVO NO EXISTE");
                    } catch (IOException ex) {
                        Logger.getLogger(PSP_Tarea06_RestricciónAcceso.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(PSP_Tarea06_RestricciónAcceso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    System.out.println("Formato de fichero incorrecto");
                    logger.log(Level.SEVERE, "ARCHIVO NO ES VALIDO");

                }
            } else {
                System.out.println("Formato del usuario incorrecto");
                logger.log(Level.SEVERE, "USUARIO NO ES VALIDO");
            }

            s.close();
        } catch (IOException ex) {
            Logger.getLogger(PSP_Tarea06_RestricciónAcceso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PSP_Tarea06_RestricciónAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean validarUsuario(String user) {
        Pattern pat = Pattern.compile("[a-zA-Z]{8}");
        Matcher mat = pat.matcher(user);
        return mat.find();
    }

    private static boolean validarFichero(String fichero) {
        Pattern pat = Pattern.compile("[a-zA-Z0-9]{8}\\.[a-z]{3}");
        Matcher mat = pat.matcher(fichero);
        return mat.find();
    }
}
