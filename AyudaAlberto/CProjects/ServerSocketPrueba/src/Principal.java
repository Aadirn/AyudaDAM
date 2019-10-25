
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
-Crear un ServerSocket para esperar peticiones entrantes en un puerto
-Esperar peticiones entrantes(se obtiene un socket)
-Obtener InputStream y/o OutputStream
-Usar los Streams
-Cierre flujos
-Cierre Socket
-Vuelva al principio
 */

/**
 *
 * @author Castelao
 */
public class Principal {
    
    public static void main(String[] args) {
        ServerSocket escuchador = null;
        Socket conexion=null;
        PrintWriter salida = null;
        OutputStream os = null;
        InputStream is = null;
        Scanner keyboard = null;
        String texto=null;
        try{
        escuchador=new ServerSocket(6666);
        //Se espera conexion
        conexion=escuchador.accept();
        
        //Mostrar info cliente por pantalla
            System.out.println("Cliente-> "+conexion.getInetAddress().getHostAddress()+": "+
                    conexion.getPort());
            salida= new PrintWriter(conexion.getOutputStream(),true);
            os = conexion.getOutputStream();
            keyboard = new Scanner(System.in);
            
            salida.println("Hola, quien eres?: ");
            texto=keyboard.nextLine();
            //Enviamos Mensaje al cliente
            salida.println("Hola "+texto+" bienvenido a Adrian's server");
            
            salida.println("Me caes mal, bye bye");
            
            //cerramos salida y conexion
            salida.close();
            conexion.close();
            
            //cerramos el ecuchador
            
            escuchador.close();
        } catch (IOException ex) {
            System.err.println("Error al abrir puerto elegido para escuchar"+ex.getMessage());
        }
    }
    
}
