/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Castelao
 */
public class EjercicioIO22 {

    /*public class InvalidWordFound extends Throwable{
        
    }*/
    public static void invalidWord(String path,String palabraClave) throws PalabraClaveException {
        try (BufferedReader invalida = new BufferedReader(new FileReader(path))) {
            int fila=0;
            String lineaLect;
            while ((lineaLect = invalida.readLine()) != null) {
                fila++;
                if (lineaLect.contains(palabraClave)){
                    throw new PalabraClaveException(fila,lineaLect.indexOf(palabraClave));
                }
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = "C:\\temp\\Palabrainvalidaprueba.txt";
        String palabraClave="patata";
        
        try{
        invalidWord(path,palabraClave);
        }catch(PalabraClaveException pc){
            System.err.println(pc.getMessage());
        }
    }
}
