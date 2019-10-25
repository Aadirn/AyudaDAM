/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio07;

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
public class EjercicioIO07 {

    public static void escribeTexto(String path, String texto) {
        try (Writer escrituraTxt = new FileWriter(path)) {
            escrituraTxt.write(texto + "\n");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void leerTxtMayus(String path){
        try(Reader lector = new FileReader(path)){
            int intLectura;
            while((intLectura=lector.read())!=-1){
                char c = (char) intLectura;
                if(Character.isUpperCase(c)){
                    System.out.print(Character.toLowerCase(c));
                } else{
                    System.out.print(Character.toUpperCase(c));
                }
                
            }
            System.out.println("\n");
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = JOptionPane.showInputDialog("Introduce ruta del fichero");
        String texto = JOptionPane.showInputDialog("Introduce texto para el fichero");
        path = path.replaceAll("\\\\", "\\\\");
        escribeTexto(path, texto);
        leerTxtMayus(path);
    }

}
