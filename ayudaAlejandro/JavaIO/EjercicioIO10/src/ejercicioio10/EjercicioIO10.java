/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio10;

import java.io.File;

/**
 *
 * @author Castelao
 */
public class EjercicioIO10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File file = new File("c:\\temp\\texto01.txt");
            double tamanho = file.length();
            System.out.println("Tamaño en bytes: " + tamanho + " B");
            System.out.println("Tamaño en Kbytes: " + tamanho / 1024 + " KB");
            System.out.println("Tamaño en Mbytes: " + tamanho / 1048576 + " MB");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
