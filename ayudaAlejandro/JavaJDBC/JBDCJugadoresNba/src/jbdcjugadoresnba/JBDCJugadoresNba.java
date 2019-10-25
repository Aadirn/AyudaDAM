/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdcjugadoresnba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JBDCJugadoresNba {

    private static String cambiarProcedencia(String pais) {

        switch (pais) {
            case "Brazil":
            case "Argentina":
                return pais + "(LATAM)";
            case "Senegal":
                return pais + "(Africa)";
            case "China":
            case "Turkey":
                return pais + "(Asia)";
            case "Croatia":
            case "Italy":
            case "Spain":
            
                return pais + "(Europe)";
            default:
                return pais;
        }
    }

    public static void main(String[] args) {
        String urlConUsuario = "jdbc:mysql://localhost/NBA?user=root&password=1234";
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        List<Integer> codigo = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement();
                Statement stUpdate = conn.createStatement();) {
            conn.setAutoCommit(false);
            ResultSet nbaRs = statement.executeQuery("select nombre,codigo,procedencia from jugadores where procedencia in('Brazil','Argentina','China','Croatia','Italy','Spain','Senegal','Turkey')");
            while (nbaRs.next()) {
                //numeroLineas++;
                System.out.println("Jugadores: "
                        + nbaRs.getString("nombre") + "      Procedencia:"
                        + nbaRs.getString("procedencia") + "      Codigo:"
                        + nbaRs.getString("codigo"));
                /*int maxId=getId.getInt("id_jugador");*/
                int actualizaJugadores = stUpdate.executeUpdate("update jugadores set procedencia ='" + cambiarProcedencia(nbaRs.getString("procedencia")) + "' where codigo = " + nbaRs.getInt("codigo"));
                codigo.add(nbaRs.getInt("codigo"));
            }
            System.out.println("\n*****************************************************************************\n");
            String query = "select * from jugadores where codigo in (";
            
            Iterator iter= codigo.iterator();
            while(iter.hasNext()){
                query=query+iter.next();
                
                if(iter.hasNext()){
                query+=",";
                }
            }
            
            query+=")";
            
            
            System.out.println("Query: "+query);
            
            System.out.println("\n*****************************************************************************\n");
            
            ResultSet jugadoresRs = statement.executeQuery(query);
            
            while(jugadoresRs.next()){
                System.out.println("Codigo: "+jugadoresRs.getInt("codigo")
                +"  Nombre: "+jugadoresRs.getString("nombre")
                +"  Procedencia: "+jugadoresRs.getString("procedencia"));
            }
            conn.rollback();
        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }
    }

}
