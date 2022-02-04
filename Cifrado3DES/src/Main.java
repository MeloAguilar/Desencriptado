import Clases.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        menu(sc);
    }

private static String pedirFicheroPaLaClave(Scanner sc){
    System.out.println("Escribe el nombre del fichero donde quieres que se genere la clave");
        return sc.nextLine();
}

private static void opcion1(Scanner sc){
        Clave.generarClave(pedirFicheroPaLaClave(sc));
}

private static void menu(Scanner sc){


    int eleccion = 0;
        do{
            System.out.println("Elige una opcion \n" +
                    "1 -> Generar Clave \n" +
                    "0 -> Salir");
            String input = sc.nextLine();
            try {
                eleccion = Integer.parseInt(input);
            }catch(NumberFormatException ex){
                System.out.println("No es una opcion válida");
                eleccion = -1;
            }
            switch(eleccion){
                case 1: opcion1(sc);
            }
        }while(eleccion != 0);

}
}
