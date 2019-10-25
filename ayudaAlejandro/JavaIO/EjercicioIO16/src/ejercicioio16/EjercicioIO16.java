/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio16;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Castelao
 */
public class EjercicioIO16 {

    private static final int TAMANHO_BUFFER = 1024;

    private static byte[] leerFichero(String path) {
        byte[] buffer = new byte[TAMANHO_BUFFER];
        byte[] guardado = null;
        try (FileInputStream lectura = new FileInputStream(path)) {
            ByteArrayOutputStream guardar = new ByteArrayOutputStream();
            int leido;
            while ((leido = lectura.read(buffer)) != -1) {
                guardar.write(buffer, 0, leido);

            }
            return guardar.toByteArray();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private static void escribirFichero(InputStream input, String path) {
        try (FileOutputStream escribidor = new FileOutputStream(path)) {
            int leidos;
            byte[] pp = new byte[TAMANHO_BUFFER];
            while((leidos=input.read(pp))!=-1){
                escribidor.write(pp, 0, leidos);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = "C:\\temp\\yatusaes\\ohos.jpg";
        String path2 ="C:\\temp\\yatusaes\\ohos2.jpg";
        ByteArrayInputStream contenedor = new ByteArrayInputStream(leerFichero(path));
        byte[] contenido = leerFichero(path);
        escribirFichero(contenedor, path2);

    }

}
