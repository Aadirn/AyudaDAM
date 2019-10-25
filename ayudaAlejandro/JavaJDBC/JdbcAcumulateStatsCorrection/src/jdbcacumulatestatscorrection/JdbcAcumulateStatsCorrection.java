/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcacumulatestatscorrection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Castelao
 */
public class JdbcAcumulateStatsCorrection {
    
    public static void insertarEquipos(Connection conn){
        String INSERT_EQUIPOS="INSERT INTO clasificacion(equipo,victorias_local,victorias_visitante,"
                + "total_puntos_local,total_puntos_visitante) "
                + "select nombre,0,0,0,0 from equipos";
        try(Statement state = conn.createStatement()){
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }
    }

    public static void crearTabla(Statement st) throws SQLException{
        int tablaClas = st.executeUpdate("create table if not exists clasificacion ("
                    +"nombre VARCHAR(30)," 
                    +"victorias_local int," 
                    +"victorias_visitante int," 
                    +"total_puntos_local int(5)," 
                    +"total_puntos_visitante int(5));");
    }
    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user="root";
        String password="1234";
        try(Connection conn = DriverManager.getConnection(urlSinUsuario,user,password);
                PreparedStatement origen = conn.prepareStatement("select");
                Statement tablaSt = conn.createStatement();){
            /*1.-Crear la nueva tabla "clasificacion"
              2.-Partiendo de la tabla partidos, guardar con cada equipo sus victorias
            cmo local, como visitante, total puntos como local y como visitante*/
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
