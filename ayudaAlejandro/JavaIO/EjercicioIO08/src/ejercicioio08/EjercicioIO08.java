/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author Castelao
 */
public class EjercicioIO08 {

    public static String loadFile(String filename) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lectura = new BufferedReader(new FileReader(filename))) {
            String linea;

            while ((linea = lectura.readLine()) != null) {
                contenido.append(linea);
            }
        } catch (IOException ex) {
            return ex.getMessage();
        }
        return contenido.toString();
    }

    public static void writeFile(Reader input, String outputFilename) {
        try (BufferedWriter escribidor = new BufferedWriter(new FileWriter(outputFilename))) {

            int read;
            while ((read = input.read()) != -1) {
                char letra = (char) read;
                if (letra == ' ') {
                    letra = '-';
                }
                escribidor.write(letra);
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void main(String[] args) throws FileNotFoundException {

        String cosa = loadFile("C:\\temp\\texto01.txt");
        System.out.println("Copia completada");
        writeFile(new StringReader(cosa), "C:\\temp\\copia.txt");

    }

}
