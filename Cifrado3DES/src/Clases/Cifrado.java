package Clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Cifrado {

    private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede";

    public static boolean cifrarFichero(Cipher cifrado, String nomFich) {

        try (FileInputStream fis = new FileInputStream(nomFich);
             FileOutputStream fos = new FileOutputStream(nomFich + ".encript");
             BufferedInputStream is = new BufferedInputStream(fis);
             BufferedOutputStream os = new BufferedOutputStream(fos)) {
            byte[] buff = new byte[cifrado.getBlockSize()];
            while (is.read(buff) != -1) {
                os.write(cifrado.update(buff));
            }
            os.write(cifrado.doFinal());
            return true;
        } catch (IllegalBlockSizeException e) {
            System.out.println("Tamaño de bloque no válido.");
            e.printStackTrace();
            return false;
        } catch (BadPaddingException e) {
            System.out.println("Excepción con relleno.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("ERROR: de E/S encriptando fichero");
            return false;
        }

    }


}
