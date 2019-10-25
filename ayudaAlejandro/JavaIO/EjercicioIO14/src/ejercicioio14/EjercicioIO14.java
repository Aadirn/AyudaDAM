/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio14;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Castelao
 */
public class EjercicioIO14 {

    private static final int TAMANHO_BUFFER = 1024;

    public static void copiaImg(String read, String cpy) {
        try (InputStream imgRead = new BufferedInputStream(new FileInputStream(read))) {
            OutputStream imgCpy = new FileOutputStream(cpy);
            byte[] buffer = new byte[TAMANHO_BUFFER];
            int leidos;
            while ((leidos=imgRead.read(buffer)) != -1) {
                imgCpy.write(buffer, 0, leidos);
            }
            System.out.println("El copiado ha finalizado");
        } catch (FileNotFoundException fnf) {
            System.err.println(fnf.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        String pathLectura="C:\\temp\\yatusaes\\ohos.jpg";
        String pathEscritura="C:\\temp\\yatusaes\\ohos(3).jpg";
        copiaImg(pathLectura,pathEscritura);
    }
}
