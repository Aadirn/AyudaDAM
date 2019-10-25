/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio04;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 *
 * @author Castelao
 */
public class EjercicioIO04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Reader contarChars = new FileReader("C:\\temp\\texto01.txt")) {
            
            String charContar;
            int contadorLetra = 0;
            Scanner keyboard = new Scanner(System.in);

            System.out.println("Que letra quiere contar?: ");
            charContar = keyboard.nextLine();

            int letraLeida;
            while ((letraLeida = contarChars.read()) != -1) {

                char letraChar = (char) letraLeida;
                if (charContar.toLowerCase().charAt(0) == letraChar || charContar.toUpperCase().charAt(0) == letraChar) {
                    contadorLetra++;
                }
                
                System.out.print(letraChar);
                
            }
            System.out.println("\nSu letra a contar ha aparecido " + contadorLetra + " veces.");
            
        } catch (IOException ex) {
            
            System.err.println(ex.getMessage());
        }
    }

}
