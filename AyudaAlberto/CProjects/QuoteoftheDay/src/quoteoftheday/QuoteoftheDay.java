/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quoteoftheday;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class QuoteoftheDay {

    public static void main(String[] args) {
        Socket conexion = null;
        InputStream escribidor = null;
        int charact;
        try {
            conexion = new Socket("localhost", 17);
            System.out.println(conexion.getLocalAddress().getHostAddress() + ":" + conexion.getLocalPort());
            System.out.println(conexion.getInetAddress().getHostAddress() + ":" + conexion.getPort());

            escribidor = conexion.getInputStream();
            while ((charact = escribidor.read()) != -1) {
                System.out.print((char) charact);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                escribidor.close();
                conexion.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
