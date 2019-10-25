/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio19;

import com.castelao.common.utilities.GenAleatoriosUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Castelao
 */
public class EjercicioIO19 {
    
    public static void escribirAleatorios(String path, int cantidad){
        try(DataOutputStream escribidor = new DataOutputStream(new FileOutputStream(path))){
            int[]aleatorios=GenAleatoriosUtil.enterosAleatorios(cantidad, -1, 100);
            for(int i =0;i<cantidad;i++){
            escribidor.writeInt(aleatorios[i]);
            }
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public static void leerNumeros(String path) throws ValorNegativoException{
        try(DataInputStream lecturiciador = new DataInputStream(new FileInputStream(path))){
            try{
                while(true){
                    int numLeido=lecturiciador.readInt();
                    System.out.println(numLeido);
                    if(numLeido<0){
                        throw new ValorNegativoException("Se ha leido un numero negativo");
                    }
                }
            }catch(EOFException eof){
                System.err.println("No hay más numeros");
            }
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String path="c:\\temp\\aleatorios.dat";
        escribirAleatorios(path, 100);
        try{
            leerNumeros(path);
        }catch(ValorNegativoException vn){
            System.out.println(vn.getMessage());
        }
        
    }
    
}
