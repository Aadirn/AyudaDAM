/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctablecopy;

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
public class JdbcTableCopy {

    /**
     * recorrer e insertar
     * @param args
     */
    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/nba";
        String urlSinUsuarioCastelao = "jdbc:mysql://localhost/castelao";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                PreparedStatement origen = conn.prepareStatement("select codigo,nombre,procedencia,altura,peso,posicion,nombre_equipo from jugadores");
                Connection conn2 = DriverManager.getConnection(urlSinUsuarioCastelao, user, password);
                PreparedStatement insertar = conn2.prepareStatement("insert into tablacopia(codigo,nombre,procedencia,altura,peso,posicion,nombre_equipo) values(?,?,?,?,?,?,?)");
                Statement createTable = conn2.createStatement()) {
                
            int nuevaTabla = createTable.executeUpdate("create table if not exists tablacopia ("
                    + "codigo INT(11) NOT NULL," 
                    +"nombre VARCHAR(30)," 
                    +"procedencia VARCHAR(20)," 
                    +"altura VARCHAR(4)," 
                    +"peso INT(11)," 
                    +"posicion VARCHAR(5)," 
                    +"nombre_equipo VARCHAR(20));");
            ResultSet jugOrigen = origen.executeQuery();
            while (jugOrigen.next()) {
                //ResultSet copia = statement2.executeQuery();
                insertar.setInt(1, jugOrigen.getInt("codigo"));
                insertar.setString(2, jugOrigen.getString("nombre"));
                insertar.setString(3, jugOrigen.getString("procedencia"));
                insertar.setString(4, jugOrigen.getString("altura"));
                insertar.setInt(5, jugOrigen.getInt("peso"));
                insertar.setString(6, jugOrigen.getString("posicion"));
                insertar.setString(7, jugOrigen.getString("nombre_equipo"));
                insertar.executeUpdate();
                
                
            }
            insertar.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
