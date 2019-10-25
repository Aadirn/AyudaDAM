/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio15;

import com.castelao.common.utilities.GenAleatoriosUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Castelao
 */
public class EjercicioIO15 {

    public static void escritura(String path, int randomCant) {
        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(path, true))) {
            int[] aleatorios = GenAleatoriosUtil.aleatorio(randomCant, 100);
            for (int i = 0; i < randomCant; i++) {
                writer.writeInt(aleatorios[i]);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void lectura(String path) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(path))) {
            int leido;
            try {
                while (true) {
                    System.out.println(reader.readInt());
                }
            } catch (EOFException eof) {
                System.err.println("No hay más numeros.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());;
        }

    }

    public static void main(String[] args) {
        String path = JOptionPane.showInputDialog("Introduzca la ruta del fichero");
        int randomCant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de numeros aleatorios a generar"));
        escritura(path, randomCant);
        lectura(path);

    }

}
