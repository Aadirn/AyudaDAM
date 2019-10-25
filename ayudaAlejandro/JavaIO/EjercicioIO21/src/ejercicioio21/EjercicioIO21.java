/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio21;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Castelao
 */
public class EjercicioIO21 {

    public static void serializar(String path, Customer custom){
        try (ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream(path))) {
            serializador.writeObject(custom);
            System.out.println("Se ha serializado el objeto");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void deserializar(String path) {
        try(ObjectInputStream deserializador = new ObjectInputStream(new FileInputStream(path))){
            Customer leible =(Customer)deserializador.readObject();
            System.out.println(leible.toString());
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Pepe", "24/04/1994",15, 1525.25);
        //Customer customer2 = new Customer("Carlinhos", "04/12/1990",87, 700.25);
        //String path2="C:/temp/Customer2.txt";
        String path="C:/temp/Customer.txt";
        System.out.println(customer);
        //System.out.println(customer2);
        serializar(path, customer);
        //serializar(path2, customer2);
        deserializar(path);
        //deserializar(path2);
        

    }
}
