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
public class IOCharArrayExample {

    private static void escribeEnStream(Writer escritor) throws IOException {
        String textoEscritura;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Escriba una linea de texto, cuando quiera terminar escriba un solo punto: ");
        do {
            textoEscritura = keyboard.nextLine();
            if (!textoEscritura.equals(".")) {
                escritor.write(textoEscritura + "\n");
            }
        } while (!textoEscritura.equals("."));
    }

    public static void main(String[] args) {
        try (Writer escrituraTxt = new FileWriter("C:\\temp\\texto02.txt")) {
            escribeEnStream(escrituraTxt);
            
        } catch (IOException ex) {
            System.err.println("Error en la escritura del archivo, el error se especifica a continuacion: " + ex.getMessage());
        }
        //char[] texto = new char[1024];
        /*try(Writer escribeEnMemoria = new CharArrayWriter()){
            escribeEnStream(escribeEnMemoria);
            System.out.println("Contenido: "+escribeEnMemoria.toString());
        }catch(Exception ex){
            System.err.println("Ha ocurrido un error inesperado "+ex.getMessage());
        }*/
    }
}
