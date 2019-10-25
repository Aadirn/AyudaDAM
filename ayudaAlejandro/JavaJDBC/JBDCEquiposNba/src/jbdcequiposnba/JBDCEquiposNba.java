/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdcequiposnba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JBDCEquiposNba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String urlConUsuario = "jdbc:mysql://localhost/NBA?user=root&password=1234";
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        int numeroLineas=0;
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement();) {
            ResultSet nba1Rs = statement.executeQuery("select * from equipos");
            while (nba1Rs.next()) {
                numeroLineas++;
                System.out.println("Equipo: " 
                        +nba1Rs.getString("nombre") +"      Ciudad:"
                        +nba1Rs.getString("ciudad") +"      Conferencia:"
                        +nba1Rs.getString("conferencia") +"     Division:"
                        +nba1Rs.getString("division"));
            }
            System.out.println("\nTotal lineas: "+numeroLineas);
            System.out.println("*************************************************");
            /*ResultSet getId = statement.executeQuery("select max(id_jugador) from jugadores");
            while(getId.next()){
                int maxId=getId.getInt("id_jugador");
            }
            */int creaEquipo = statement.executeUpdate("insert into equipos (nombre,ciudad) values ('Obradoiro','sevilla')");
            System.out.println("Fila creada: "+creaEquipo);
            
            System.out.println("**********************************************");
            
            
        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }
    }
    
}
