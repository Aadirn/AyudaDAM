/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcacumulatestats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Castelao
 */
public class JdbcAcumulateStats {

    public static int ganadorLocal(Connection conn,String nombreEquipo) throws SQLException{
        int victoriasLocal=0;
        PreparedStatement ganador = conn.prepareStatement("select equipo_local,puntos_local,puntos_visitante from partidos where equipo_local=?");
        ganador.setString(1, nombreEquipo);
        ResultSet victorias = ganador.executeQuery();
        while(victorias.next()){
            if(victorias.getInt("puntos_local")>victorias.getInt("puntos_visitante")){
                victoriasLocal++;
            }else{
                ganadorVisitante(conn, nombreEquipo);
            }
        }
        victorias.close();
        return victoriasLocal;
    }
    public static int ganadorVisitante(Connection conn, String nombreEquipo) throws SQLException{
        int victoriasVisitante=0;
        PreparedStatement ganador = conn.prepareStatement("select equipo_visitante,puntos_local,puntos_visitante from partidos where equipo_visitante=?");
        ganador.setString(1, nombreEquipo);
        ResultSet victorias = ganador.executeQuery();
        while(victorias.next()){
            if(victorias.getInt("puntos_local")<victorias.getInt("puntos_visitante")){
                victoriasVisitante++;
            }
        }
        victorias.close();
        return victoriasVisitante;
    }
    public static void crearTabla(Statement st) throws SQLException{
        int tablaClas = st.executeUpdate("create table if not exists clasificacion ("
                    +"equipo_local VARCHAR(30),"
                    +"equipo_visitante VARCHAR(30),"
                    +"victorias_local int," 
                    +"victorias_visitante int," 
                    +"total_puntos_local int(5)," 
                    +"total_puntos_visitante int(5));");
    }
    public static int puntosTotales(Connection conn,String nombreEquipo, boolean esVisistante) throws SQLException{
        int sumaLocal=0;
        int sumaVisitante=0;
        if(esVisistante){
            PreparedStatement totalLocal = conn.prepareStatement("select equipo_local,puntos_local,puntos_visitantes from partidos where equipo_local=?");
            totalLocal.setString(1, nombreEquipo);
            ResultSet acumulaL = totalLocal.executeQuery();
            while(acumulaL.next()){
                sumaLocal=sumaLocal+acumulaL.getInt("puntos_local");
            }
            return sumaLocal;
        }else{
            PreparedStatement totalVisitante = conn.prepareStatement("select equipo_visitante,puntos_local,puntos_visitantes from partidos where equipo_visitante=?");
            totalVisitante.setString(1, nombreEquipo);
            ResultSet acumulaV = totalVisitante.executeQuery();
            while(acumulaV.next()){
                sumaVisitante=sumaVisitante+acumulaV.getInt("puntos_local");
            }
            return sumaVisitante;
        }
        
    }
    /*public static String seleccionarEquipo(){
        
    }*/
    public static void insertarDatos(Connection conn, PreparedStatement original) throws SQLException{
        PreparedStatement insertar = conn.prepareStatement("insert into clasificacion(equipo_local, equipo_visitante, victorias_local, victorias_visitante, "
                + "total_puntos_local, total_puntos_visitante) values(?,?,?,?,?,?)");
        ResultSet origen = original.executeQuery();
        while(origen.next()){
            insertar.setString(1, origen.getString("equipo_local"));
            insertar.setString(2, origen.getString("equipo_visitante"));
            insertar.setInt(3,ganadorLocal(conn, origen.getString("equipo_local")));
            insertar.setInt(4,ganadorVisitante(conn, origen.getString("equipo_visitante")));
            insertar.setInt(5,puntosTotales(conn, origen.getString("equipo_local"), false));
            insertar.setInt(5,puntosTotales(conn, origen.getString("equipo_visitante"), true));
            insertar.executeUpdate();
        }
        insertar.close();
    }
    
    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user="root";
        String password="1234";
        try(Connection conn = DriverManager.getConnection(urlSinUsuario,user,password);
                PreparedStatement origen = conn.prepareStatement("select equipo_local, equipo_visitante,puntos_local,puntos_visitante from partidos");
                Statement tablaSt = conn.createStatement();){
            /*1.-Crear la nueva tabla "clasificacion"
              2.-Partiendo de la tabla partidos, guardar con cada equipo sus victorias
            cmo local, como visitante, total puntos como local y como visitante*/
            crearTabla(tablaSt);
            insertarDatos(conn, origen);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
