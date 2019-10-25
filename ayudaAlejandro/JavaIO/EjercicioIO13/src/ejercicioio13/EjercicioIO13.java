/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio13;

import java.io.File;

/**
 *
 * @author Castelao
 */
public class EjercicioIO13 {

    public static void dirRecurs(String path) {
        File listaFich = new File(path);
        File[] ficheros = listaFich.listFiles();
        for (File fichero : ficheros) {
            if (fichero.isDirectory()) {
                System.out.println("Directorio: " + fichero.getAbsolutePath());
                dirRecurs(fichero.getAbsolutePath());
            } else {
                System.out.println(fichero.getAbsolutePath());
            }
        }

    }

    public static void main(String[] args) {
        String path = "C:\\temp";
        dirRecurs(path);
    }

}
