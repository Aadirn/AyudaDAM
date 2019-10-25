/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author Castelao
 */
public class EjercicioIO02 {

    public static void main(String[] args) {
        try (Writer escrituraTxt = new FileWriter("C:\\temp\\texto02.txt")) {
            String textoEscritura;
            Scanner keyboard = new Scanner(System.in);
            
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
