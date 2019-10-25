/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio20;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class EjercicioIO20 {

    private static final int TAMANHO_BUFFER = 1024;

    public static void writeSecret(String msg, String path) {
        int pos = TAMANHO_BUFFER + 4;
        int i = 0;
        File file = new File(path);
        try (RandomAccessFile secreto = new RandomAccessFile(file, "rw")) {
            byte[] buffer = new byte[TAMANHO_BUFFER];
            new Random().nextBytes(buffer);
            secreto.writeInt(pos);
            secreto.write(buffer);
            secreto.writeUTF(msg);
            do {
                secreto.write(buffer);
                i++;
            } while (i != 10);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void showSecret(File[] files,String path) {
        File listaFich = new File(path);
        try (RandomAccessFile input = new RandomAccessFile(path, "rw")) {
            int posicion=input.readInt();
            input.seek(posicion);
            String mensaje = input.readUTF();
            System.out.println("El archivo "+ listaFich.getName()+" Tiene el mensaje "+mensaje);
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String path="C:/temp/sicrets/agapitoDisousa.dat";
        writeSecret("Haudi!", path);
        showSecret(new File(path).listFiles(), path);
    }

}
