/*
IOLongestWord – Escribe un programa que lea
un fichero de texto y devuelva la palabra más
larga.
 */
package ejercicioio06;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 *
 * @author Castelao
 */
public class EjercicioIO06 {

    public static void main(String[] args) {
        try(Reader longestWord = new FileReader("C:\\temp\\textoPalabraLarga.txt")){
            Scanner escaneo = new Scanner(longestWord);
                String palabraMax="";
            while(escaneo.hasNext()){
                String palabra=escaneo.next();
                if(palabra.length()> palabraMax.length()){
                    palabraMax=palabra;
                    
                }
            }
            
            System.out.println("La palabra mas larga es: "+palabraMax);
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
}
