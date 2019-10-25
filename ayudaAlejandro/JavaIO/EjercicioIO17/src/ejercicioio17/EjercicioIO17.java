/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio17;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author Castelao
 */
public class EjercicioIO17 {

    public static void downloadUrl(String urlString) {
        try (OutputStream output = new FileOutputStream("C:\\temp\\descarga.html")) {

            int leido;
            byte[] buffer = new byte[1024];

            try (InputStream input = new URL(urlString).openStream()) {

                while ((leido = input.read(buffer)) != -1) {
                    output.write(buffer, 0, leido);
                }
                System.out.println("Se ha descargado la página.");

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public static void main(String[] args) {
        String urlString = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        downloadUrl(urlString);
    }

}
