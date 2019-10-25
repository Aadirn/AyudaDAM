/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdcresultsettype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Castelao
 */
public class JBDCResultSetType {

    
    public static void main(String[] args) {
        String urlConUsuario = "jdbc:mysql://localhost/NBA?user=root&password=1234";
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        int numeroLineas = 0;
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);) {
            
            ResultSet kobeRs = statement.executeQuery("select e.temporada,e.puntos_por_partido,e.asistencias_por_partido,e.tapones_por_partido,e.rebotes_por_partido"
                    + " from jugadores j inner join estadisticas e on j.codigo=e.jugador where j.nombre='Kobe Bryant' order by e.puntos_por_partido asc");
                
            if(kobeRs.isBeforeFirst()){
                System.out.println("Empezamos antes del primero");
            }
            kobeRs.last();
            if(kobeRs.isLast()){
                System.out.println("Nos movemos al ultimo");
            }
                while(!kobeRs.isBeforeFirst()){
                    if(kobeRs.isFirst()){
                    System.out.println("Hemos vuelto al primero");
                    }
                System.out.println("Estadisticas Kobe:\tPuntos:"
                        + kobeRs.getString("puntos_por_partido") + "\tAsistencias:"
                        + kobeRs.getString("asistencias_por_partido") + "\tTapones:"
                        + kobeRs.getString("tapones_por_partido") + "\tRebotes:"
                        + kobeRs.getString("rebotes_por_partido"));
                kobeRs.previous();
                }
            
            System.out.println("*************************************************");
            /*ResultSet getId = statement.executeQuery("select max(id_jugador) from jugadores");
            while(getId.next()){
                int maxId=getId.getInt("id_jugador");
            }
             */

        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }
    }

}
