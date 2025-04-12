/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package restriccionAcceso;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class PSP_Tarea06_RestriccionAcceso {
    
    // Directorio permitido
    private static final String ALLOWED_DIRECTORY = "C:/datos";
    
    private static final Logger logger = Logger.getLogger("MyLog");

    public static void main(String[] args) {
        try {
            // Configuración del logger (ahora en C:/datos)
            configureLogger();
            
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

                    // Validar y leer el archivo
                    readFileSecurely(fichero);
                } else {
                    System.out.println("Formato de fichero incorrecto");
                    logger.log(Level.SEVERE, "ARCHIVO NO ES VALIDO");
                }
            } else {
                System.out.println("Formato del usuario incorrecto");
                logger.log(Level.SEVERE, "USUARIO NO ES VALIDO");
            }

            s.close();
        } catch (SecurityException ex) {
            logger.log(Level.SEVERE, "Acceso denegado: " + ex.getMessage());
            System.out.println("Error de seguridad: " + ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error: " + ex.getMessage());
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void configureLogger() throws SecurityException, IOException {
        // Crear directorio de logs si no existe
        File logDir = new File(ALLOWED_DIRECTORY + "/logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        
        FileHandler fh = new FileHandler(ALLOWED_DIRECTORY + "/logs/MyLogFile.log", true);
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        fh.setFormatter(new SimpleFormatter());
    }

    private static void readFileSecurely(String filename) {
        BufferedReader br = null;
        try {
            // Construir la ruta segura
            Path allowedPath = Paths.get(ALLOWED_DIRECTORY).toAbsolutePath().normalize();
            Path filePath = Paths.get(ALLOWED_DIRECTORY, filename).toAbsolutePath().normalize();
            
            // Verificar que el archivo está dentro del directorio permitido
            if (!filePath.startsWith(allowedPath)) {
                throw new SecurityException("Acceso denegado: El archivo no está en el directorio permitido");
            }

            File archivo = filePath.toFile();
            if (archivo.isFile()) {
                logger.log(Level.FINEST, "ARCHIVO EXISTE");

                System.out.println("Contenido del fichero:");
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                logger.log(Level.FINEST, "ARCHIVO MOSTRADO POR PANTALLA");
            } else {
                throw new FileNotFoundException("El archivo no existe");
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "ARCHIVO NO EXISTE");
            System.out.println("Error: El archivo no existe");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error de E/S: " + ex.getMessage());
            System.out.println("Error al leer el archivo");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, "Error al cerrar el archivo");
                }
            }
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