/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio11;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Castelao
 */
public class EjercicioIO11 {

    public static void main(String[] args) {
        Pattern separador = Pattern.compile("<stop[0-9]>");
        String path = "C:\\temp\\telegrama.txt";
        File archivito = new File(path);
        try (Scanner telegrama = new Scanner(archivito)) {
            telegrama.useDelimiter(separador);

            while (telegrama.hasNext()) {
                System.out.println("(" + telegrama.next() + ")");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
