
package calculadoraonline;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CalculadoraOnline {

    private static int suma(int primero, int segundo) {
        int res;
        res=primero+segundo;
        return res;
    }

    private static int resta(int primero, int segundo) {
        int res;
        res=primero-segundo;
        return res;
    }

    private static int multiplicacion(int primero, int segundo) {
        int res;
        res=primero*segundo;
        return res;
    }

    private static int division(int primero, int segundo) {
        int res;
        res=primero/segundo;
        return res;
    }

    private static int parseadorResult(String linea) throws PeticionErrorException, NoSuchElementException,NumberFormatException {
        StringTokenizer st = null;
        int num1 = 0;
        int num2 = 0;
        int resFin = 0;
        String comprobar = null;
        st = new StringTokenizer(linea, "+-/*", true);
        while (st.hasMoreTokens()) {
            num1 = Integer.parseInt(st.nextToken().trim());
            comprobar = st.nextToken();
            num2 = Integer.parseInt(st.nextToken().trim());
        }

        switch (comprobar) {
            case "+":
                resFin = suma(num1, num2);
                break;
            case "-":
                resFin = resta(num1, num2);
                break;
            case "*":
                resFin = multiplicacion(num1, num2);
                break;
            case "/":
                resFin = division(num1, num2);
                break;
            default:
                throw new PeticionErrorException("MAL FORMATO DE OPERACION");
        }
        return resFin;
    }

    public static void main(String[] args) {
        ServerSocket escuchador;
        Socket conexion;
        PrintStream salida;
        Scanner keyboard = null;
        InputStream is = null;
        String peticion;
        String peticionLowerCase;
        int resultado;
        try {
            
            escuchador = new ServerSocket(12345);
            do{
                
            conexion = escuchador.accept();

            keyboard = new Scanner(conexion.getInputStream());

            salida = new PrintStream(conexion.getOutputStream());
            salida.println("Bienvenido a Calculoncio");
            
            salida.print("Operacion?: ");
            salida.flush();

            peticion = keyboard.nextLine();
            if (peticion.length() == 0) {
                throw new PeticionErrorException("PETICION VACIA");
            }
            resultado = parseadorResult(peticion);

            salida.println("Resultado: " + resultado);
            salida.println("Quiere realizar otra operacion? (Y/N)");
            salida.flush();
            peticion=keyboard.nextLine();
            peticionLowerCase = peticion.toLowerCase();
            
            
            
            salida.println("Gracias por usar CalculadOn");
            salida.close();
            keyboard.close();
            conexion.close();
            }while(!peticionLowerCase.equals("n"));
            salida.flush();
            
            escuchador.close();
            

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (PeticionErrorException ex) {
            System.err.println("Error en la operacion: " + ex.getMessage());
        } catch(NoSuchElementException ex){
            System.err.println("Fallo en el tokenizer: "+ex.getMessage());
        }catch(NumberFormatException ex){
            System.err.println("No se han usado numeros en la operacion: "+ex.getMessage());
        }
    }

}
