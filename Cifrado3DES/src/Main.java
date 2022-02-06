import Clases.*;

import java.util.Scanner;

public class Main {
    public static final String MENSAJE_ERROR = "La elección debe ser un número entre 0 y 3";
    public static final String PEDIR_NOMARCHIVO = "Escribe el nombre del fichero donde quieres que se genere la clave";
    public static final String PEDIR_TIPO_ENCRIPTADO = "Elija entre los tres tipos de encriptado posibles. \n" +
            "1 -> AES \n" +
            "2 -> DES \n" +
            "3 -> DESede";
    public static final String PEDIR_TIPO_CLAVE = "Escribe: \n" +
            "1 -> AES \n" +
            "2 -> DES \n" +
            "3 -> DESede\n" +
            "0 -> Atrás";
    public static final String RUTA_CLAVES = "C:\\Users\\GL512\\IdeaProjects\\Desencriptado\\Cifrado3DES\\src\\Claves\\";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menu(sc);
    }


    /**
     * @param sc
     * @param peticion
     * @return
     */
    private static String pedir(Scanner sc, String peticion) {
        System.out.println(peticion);
        return sc.nextLine();
    }

    /**
     * @param sc
     * @return
     */
    private static int eleccionTipoClave(Scanner sc) {
        String eleccionString = "";
        int eleccion = -1;
        do {
            eleccionString = pedir(sc, PEDIR_TIPO_CLAVE);
            try {
                eleccion = Integer.parseInt(eleccionString);
            } catch (Exception e) {
                System.out.println(MENSAJE_ERROR);
                eleccion = -1;
            }
        } while (eleccion == -1);
        return eleccion;
    }


    /**
     * Desde aquí se llama al método que pedirá al usuario
     * introducir el tipo de clave de cifrado que prefiere
     * para su fichero.
     *
     * @param sc
     */
    private static void opcion1(Scanner sc, int tipoClave) {
        switch (tipoClave) {
            case 1:
                Clave.generarClaveAES(pedir(sc, PEDIR_NOMARCHIVO));
                break;
            case 2:
                Clave.generarClaveDESyDESede(eleccionTipoClave(sc), pedir(sc, PEDIR_NOMARCHIVO));
                break;
        }


    }


    /**
     * Menu de la app
     *
     * @param sc
     */
    private static void menu(Scanner sc) {


        int eleccion = 0;
        do {
            System.out.println("Elige una opcion \n" +
                    "1 -> Generar Clave \n" +
                    "0 -> Salir");
            String input = sc.nextLine();
            try {
                eleccion = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("No es una opcion válida");
                eleccion = -1;
            }
            switch (eleccion) {
                case 0:
                    System.out.println("Bye!");
                    break;
                case 1:
                    opcion1(sc, eleccionTipoClave(sc));
                    break;
            }
        } while (eleccion != 0);

    }
}
