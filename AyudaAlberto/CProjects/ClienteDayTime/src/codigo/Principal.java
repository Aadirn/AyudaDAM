package codigo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Principal {

    public static void main(String[] args) {
        Socket conexion = null;
        InputStream entrada = null;
        int charat;

        try {
            conexion = new Socket("localhost", 19);
            System.out.println(conexion.getLocalAddress().getHostAddress() + ":" + conexion.getLocalPort());

            System.out.println(conexion.getInetAddress().getHostAddress() + ":" + conexion.getPort());
            entrada = conexion.getInputStream();
            while ((charat = entrada.read()) != -1) {
                System.out.print((char) charat);
            }

        } catch (IOException ex) {
            System.err.println("ERROR" + ex.getMessage());
        } finally {
            try {
                entrada.close();
                conexion.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
