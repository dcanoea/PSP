package PSP_Tarea07;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("=== INICIO DEL PROCESO ===");

            // Datos de entrada
            String usuario = "David";
            String password = "Huesca";
            String textoOriginal = "Mi nombre es David y este es un texto de prueba que será cifrado y almacenado.";

            // Generar clave AES de 128 bits a partir del usuario+password
            SecretKey clave = generarClaveAES(usuario + password);
            System.out.println("-> Clave generada con éxito.");

            // Cifrar el texto original y guardarlo en un fichero
            cifrarTextoAFichero(textoOriginal, clave, "fichero.cifrado");
            System.out.println("-> Texto cifrado y guardado en: fichero.cifrado");

            // Descifrar el contenido del fichero y mostrarlo por pantalla
            String textoDescifrado = descifrarTextoDesdeFichero(clave, "fichero.cifrado");
            System.out.println("-> Texto descifrado desde fichero:");
            System.out.println(textoDescifrado);

            System.out.println("=== PROCESO FINALIZADO ===");

        } catch (Exception e) {
            e.printStackTrace(); // Mostrar errores si ocurren
        }
    }

    /**
     * Genera una clave AES de 128 bits usando una semilla determinada
     * @param semilla Texto base para generar la semilla (ej: usuario+password)
     * @return Clave secreta AES
     */
    private static SecretKey generarClaveAES(String semilla) {
        try {
            // Convertir la semilla a bytes
            byte[] seed = semilla.getBytes();

            // Crear un generador de números aleatorios con esa semilla
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed); // Determinismo: misma clave para misma semilla

            // Crear generador de claves AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, sr); // Inicializar con 128 bits y la semilla
            return keyGen.generateKey(); // Devolver la clave generada

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, devolver null
        }
    }

    /**
     * Cifra un texto y lo guarda en un fichero usando AES/ECB/PKCS5Padding
     * @param texto Texto a cifrar
     * @param clave Clave secreta AES
     * @param nombreFichero Nombre del fichero donde se guardará el contenido cifrado
     */
    private static void cifrarTextoAFichero(String texto, SecretKey clave, String nombreFichero) {
        try {
            // Preparar el cifrador con algoritmo AES, modo ECB y relleno PKCS5
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clave); // Modo cifrado

            // Convertir el texto a bytes y cifrarlo
            byte[] textoBytes = texto.getBytes("UTF-8");
            byte[] textoCifrado = cipher.doFinal(textoBytes);

            // Guardar los bytes cifrados en un fichero
            FileOutputStream fos = new FileOutputStream(nombreFichero);
            fos.write(textoCifrado);
            fos.close(); // Cerrar el fichero

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Descifra el contenido de un fichero cifrado con AES/ECB/PKCS5Padding
     * @param clave Clave secreta con la que fue cifrado el texto
     * @param nombreFichero Nombre del fichero cifrado
     * @return El texto descifrado como cadena
     */
    private static String descifrarTextoDesdeFichero(SecretKey clave, String nombreFichero) {
        try {
            // Leer todos los bytes del fichero cifrado
            FileInputStream fis = new FileInputStream(nombreFichero);
            byte[] contenido = fis.readAllBytes();
            fis.close();

            // Inicializar el cifrador para descifrar con AES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clave); // Modo descifrado

            // Descifrar los datos y convertirlos a texto
            byte[] textoDescifrado = cipher.doFinal(contenido);
            return new String(textoDescifrado, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Devolver null si ocurre error
        }
    }
}
