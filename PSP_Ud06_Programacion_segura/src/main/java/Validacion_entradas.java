
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author David Cano Escario
 */
public class Validacion_entradas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Definimos Pattern y Matcher
        Pattern pat = null;
        Matcher mat = null;

        /*
        Elementos para la validación de entradas
        x = El carácter x
        [abc] = Los caracteres a, b o c
        [a-z] = Una letra en minúscula
        [A-Z] = Una letra en mayúscula
        [a-zA-Z] = Una letra en minúscula o mayúscula
        [0-9] = Un número comprendido entre el 0 y el 9
        [a-zA-Z0-9] = Una letra en minúscula, mayúscula o un número

        
        Operadores para la validación de entradas
        [a-z]{2} = Hay que introducir dos letras en minúsculas
        [a-z]{2,5} = Hay que introducir de 2 a 5 letras en minúsculas
        [a-z]{2,} = Hay que introducir más de 2 letras en minúsculas
        hola|adios = Es la operación OR lógica y permite indicar que se introduzca el texto "hola" o "adios"
        XY = Es la operación AND lógica y permite indicar que se deben introducir dos expresiones X seguida de Y
        e(n|l) campo = Los delimitadores () permite hacer expresiones más complejas. En el ejemplo, el usuario debe introducir el texto "en campo" o "el campo"
         */
        //EJEMPLOS
        //Compilamos el patrón a utilizar con -> pat = Pattern.compile();
        //Ejemplo teléfono (000-000000)
        pat = Pattern.compile("[0-9]{3}-[0-9]{6}");

        //Ejemplo DNI
        pat = Pattern.compile("[0-9]{8}-[a-zA-Z]");

        //Ejemplo provincias andaluzas
        pat = Pattern.compile("Almería|Granada|Jaén|Huelva|Málaga|Sevilla|Cádiz|Córdoba");

        /*if (mat.find()) {
            //Coincide con el patrón
        } else {
            //NO coincide con el patrón
        }*/
        Scanner sc = new Scanner(System.in);

        // Validación del número de teléfono
        System.out.print("Introduce un número de teléfono (formato 000-000000): ");
        String telefono = sc.nextLine();

        if (validarTelefono(telefono)) {
            System.out.println("Teléfono válido");
        } else {
            System.out.println("Formato de teléfono incorrecto.");
        }

        // Validación del DNI
        System.out.print("Introduce un DNI (formato 12345678-A): ");
        String dni = sc.nextLine();

        if (validarDNI(dni)) {
            System.out.println("DNI válido");
        } else {
            System.out.println("Formato de DNI incorrecto.");
        }

        sc.close();
    }

    // Método para validar número de teléfono con expresión regular
    public static boolean validarTelefono(String telefono) {
        Pattern pat = Pattern.compile("^[0-9]{3}-[0-9]{6}$");
        Matcher mat = pat.matcher(telefono);
        return mat.find();
    }

    // Método para validar DNI con expresión regular
    public static boolean validarDNI(String dni) {
        Pattern pat = Pattern.compile("^[0-9]{8}-[a-zA-Z]$");
        Matcher mat = pat.matcher(dni);
        return mat.find();
    }
}
