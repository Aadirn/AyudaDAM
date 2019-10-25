/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio01;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Castelao
 */
public class EjercicioIO01 {

    private static final char CHAR_TO_REMOVE = ' ';

    public static void main(String[] args) {
        try (Reader lecturaTxt = new FileReader("C:\\temp\\texto01.txt")) {
            int letraLeida;
            while ((letraLeida = lecturaTxt.read()) != -1) {
                char c = (char) letraLeida;
                if (c != CHAR_TO_REMOVE) {
                    System.out.print(c);
                }
            }
            System.out.println("\n");

        } catch (IOException ex) {
            System.err.println("Ha ocurrido un fallo en la lectura del fichero, a continuacion se epecifica el fallo: " + ex.getMessage());
        }

    }

}
