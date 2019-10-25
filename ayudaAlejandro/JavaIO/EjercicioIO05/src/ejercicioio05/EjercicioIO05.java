/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Castelao
 */
public class EjercicioIO05 {

    public static void copiaTexto(String origen, String destino) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lecturaCopiar = new BufferedReader(new FileReader(origen));
                BufferedWriter escrituraCopiar = new BufferedWriter(new FileWriter(destino))) {

            String lineaLectura;
            while ((lineaLectura = lecturaCopiar.readLine()) != null) {
                contenido.append(lineaLectura);
            }
            escrituraCopiar.write(contenido.toString());
            System.out.println("El copiado ha terminado.");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        copiaTexto("C:\\temp\\textoAcopiar.txt", "C:\\temp\\textoCopia.txt");
    }

}
