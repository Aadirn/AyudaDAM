/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Castelao
 */
public class EjercicioIO18 {
    
    private static final int CANTIDAD=10000;

    public static void escribeSinBuffer(String path) {
        try (DataOutputStream noBufferWrite = new DataOutputStream(new FileOutputStream(path))) {

            for (int i = 0; i <= CANTIDAD; i++) {
                noBufferWrite.writeInt(i);
            }

            System.out.println("Prueba de escritura sin buffer terminada.");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void escribeConBuffer(String path) {
        try (DataOutputStream bufferWrite = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {

            for (int i = 0; i <= CANTIDAD; i++) {
                bufferWrite.writeInt(i);
            }

            System.out.println("Prueba de escritura con buffer terminada.");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void leeSinBuffer(String path) {
        try (DataInputStream noBufferRead = new DataInputStream(new FileInputStream(path))) {

            try {

                while (true) {
                    noBufferRead.readInt();
                }

            } catch (EOFException eof) {
                System.err.println("No hay más numeros");
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void leeConBuffer(String path) {
        try (DataInputStream bufferRead = new DataInputStream(new BufferedInputStream(new FileInputStream(path)))) {
            try {
                while (true) {
                    bufferRead.readInt();
                }
            }catch(EOFException eof){
                System.err.println("No hay más numeros");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String pathNoBuffer = "C:\\temp\\pruebaNoBuffer.txt";
        String pathConBuffer = "C:\\temp\\pruebaBuffer.txt";
        Date start, end;

        start = new Date();
        escribeSinBuffer(pathNoBuffer);
        end = new Date();
        System.out.println("Tiempo (ms) escribiendo SIN buffer: " + (end.getTime() - start.getTime()));

        start = new Date();
        escribeConBuffer(pathConBuffer);
        end = new Date();
        System.out.println("Tiempo (ms) escribiendo CON buffer: " + (end.getTime() - start.getTime()));

        start = new Date();
        leeSinBuffer(pathNoBuffer);
        end = new Date();
        System.out.println("Tiempo (ms) leyendo SIN buffer: " + (end.getTime() - start.getTime()));

        start = new Date();
        leeConBuffer(pathConBuffer);
        end = new Date();
        System.out.println("Tiempo (ms) leyendo CON buffer: " + (end.getTime() - start.getTime()));
    }

}
