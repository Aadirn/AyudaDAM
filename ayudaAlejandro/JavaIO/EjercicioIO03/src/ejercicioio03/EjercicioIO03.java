/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio03;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author Castelao
 */
public class EjercicioIO03 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean machaca = true;
        String opcionSel;
        do {
        System.out.println("Borrar lo de el archivo y hacerlo de nuevo?: ('S'SI/'N'NO)");
        opcionSel = keyboard.nextLine();
        opcionSel = opcionSel.toUpperCase();
            if (opcionSel.equals("S")) {
                machaca = false;
            } else if (opcionSel.equals("N")) {
                machaca = true;
            }
        } while (!opcionSel.equals("S") && !opcionSel.equals("N"));
        try (Writer escrituraTxt = new FileWriter("C:\\temp\\texto01.txt", machaca)) {
            String textoEscritura;
            System.out.println("Escriba una linea de texto, cuando quiera terminar escriba un solo punto: ");
            do {
                textoEscritura = keyboard.nextLine();
                if (!textoEscritura.equals(".")) {
                    escrituraTxt.write(textoEscritura + "\n");
                }
            } while (!textoEscritura.equals("."));
            escrituraTxt.close();
        } catch (IOException ex) {
            System.err.println("Error en la escritura del archivo, el error se especifica a continuacion: " + ex.getMessage());
        }
    }

}
