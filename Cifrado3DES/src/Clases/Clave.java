package Clases;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Clave {
    private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede";  // 3DES
    private static final String NOM_FICH_CLAVE = "C:\\Users\\caguilar.INFO2\\Desktop\\PSPRO\\programas_ejemplo_libro_PSP\\Tema5-TecnicasProgSegura\\03-Cifrado3DES\\Cifrado3DES\\src\\Claves\\";
    private static final String ALGORITMO_GEN_NUM_ALEAT = "SHA1PRNG";


    public static String generarClave(String ficheroClave, String rutaCompletaFicheroAEncriptar, String ficheroEncriptadoACrear) {

        try{
            KeyGenerator genClaves = KeyGenerator.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            SecureRandom srand = SecureRandom.getInstance(ALGORITMO_GEN_NUM_ALEAT);
            genClaves.init(srand);


            SecretKey clave = genClaves.generateKey();
            System.out.printf("Formato de clave: %s\n", clave.getFormat());

            SecretKeyFactory keySpecFactory = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            DESedeKeySpec keySpec = (DESedeKeySpec) keySpecFactory.getKeySpec(clave, DESedeKeySpec.class);

            byte[] valorClave = keySpec.getKey();

            System.out.printf("Longitud de clave: %d bits\n", valorClave.length*8);
            System.out.printf("Valor de la clave: [%s] en fichero %s",
                    valorHexadecimal(valorClave), NOM_FICH_CLAVE+ficheroClave);
            try(FileOutputStream fos = new FileOutputStream(NOM_FICH_CLAVE+ficheroClave+".txt")) {
                fos.write(valorClave);
            } catch (IOException e) {
                System.out.println("Error de E/S escribiendo clave en fichero");
                e.printStackTrace();
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo de generación de claves no soportado");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return NOM_FICH_CLAVE+ficheroClave;
        }



    static String valorHexadecimal(byte[] bytes) {
        String result = "";
        for (byte b : bytes) {
            result += String.format(String.format("%x", b));
        }
        return result;
    }
    /*

    byte[] valorClave = null;

        boolean ok = false;
        try (FileInputStream fisClave = new FileInputStream(NOM_FICH_CLAVE)) {

            valorClave = fisClave.readAllBytes();
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: no existe fichero de clave %s\n.", NOM_FICH_CLAVE);

        } catch (IOException e) {
            System.out.printf("ERROR: de E/S leyendo clave de fichero %s\n.", NOM_FICH_CLAVE);

        }

        Cipher cifrado = null;
        try {


            SecretKeySpec keySpec = new SecretKeySpec(key , ALGORITMO_CLAVE_SIMETRICA);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);
            SecretKey clave = keyFactory.generateSecret(keySpec);
            cifrado = Cipher.getInstance(ALGORITMO_CLAVE_SIMETRICA);
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            ok = true;
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("No existe algoritmo de cifrado %s.\n", ALGORITMO_CLAVE_SIMETRICA);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.out.println("Especificación de clave no válida.");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Clave no válida.");
            e.printStackTrace();
        }

        return cifrado;
    }
     */
}
