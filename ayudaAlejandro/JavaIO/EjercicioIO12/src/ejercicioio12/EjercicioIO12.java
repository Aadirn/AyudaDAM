/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio12;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javax.swing.JOptionPane;

/**
 *
 * @author Castelao
 */
public class EjercicioIO12 {

    public static String leerFicheros(String path) {
        StringBuilder txtFinal = new StringBuilder();
        try (Reader lector = new FileReader(path)) {
            int leido;
            while ((leido = lector.read()) != -1) {
                char letra = (char) leido;
                txtFinal.append(letra);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return txtFinal.toString();
    }
    public static void escribirFinal(String pathC, boolean sobreescribir,String A,String B){
        try (Writer escritor = new FileWriter(pathC, sobreescribir)) {
                escritor.write(A.concat(B));
                System.out.println("El fichero se ha creado");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
    }

    public static void main(String[] args) {
        String pathA = "c:\\temp\\R.txt";
        String pathB = "c:\\temp\\E2.txt";
        String pathC=pathA.substring(0, pathA.lastIndexOf("."))+"_"+ pathB.substring(pathB.lastIndexOf("\\")+1);
        String contenidoA = leerFicheros(pathA);
        String contenidoB = leerFicheros(pathB);
        File archivo = new File(pathC);
        if (archivo.exists()) {
            int eleccion = JOptionPane.showConfirmDialog(null, "El fichero ya existe, ¿Quieres sobrescribir el fichero " + pathC + "?", "Sobrescribir",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (eleccion == JOptionPane.OK_OPTION) {
                escribirFinal(pathC, false, contenidoA, contenidoB);
            } else {
                escribirFinal(pathC, true, contenidoA, contenidoB);
            }
        } else {
            escribirFinal(pathC, false, contenidoA, contenidoB);
        }

    }

}
