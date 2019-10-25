/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdcprueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Castelao
 */
public class JBDCPrueba {

    /*
    Carga el driver JBDC de MySql
    Necesario solo antes de java 1.6:
    Class.forName("com.mysql.jbdc.Driver");
    Indica la clase del driver que implementa la interface
    java.sql.Driver
     */
    public static void main(String[] args) {
        String urlConUsuario = "jdbc:mysql://localhost/liga?user=root&password=1234";
        String urlSinUsuario = "jdbc:mysql://localhost/liga?useJDBCCompliantTimezoneShift=true&LegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement();) {
            ResultSet jugadoresRs = statement.executeQuery("select id_jugador,nombre,apellido,altura from jugadores");
            while (jugadoresRs.next()) {
                System.out.println("Jugador: " 
                        /*Podemos acceder por nombre o posicion*/
                        +jugadoresRs.getInt("id_jugador")+ " "
                        +jugadoresRs.getString("nombre") + " " 
                        +jugadoresRs.getString("apellido") +" mide "
                        +jugadoresRs.getFloat("altura"));
            }
            ResultSet getId = statement.executeQuery("select max(id_jugador) from jugadores");
            while(getId.next()){
                int maxId=getId.getInt("id_jugador");
            }
            int maxId = 0;
            int newRow = statement.executeUpdate("insert jugadores (id_jugador,nombre,apellido,altura) values ("+ (maxId+1) +",'Floripondio', 'Diafoirus', 185)");
            System.out.println("Fila creada: "+newRow);
        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }
    }

}
