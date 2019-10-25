/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcinsertexample;

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
public class JdbcInsertExample {

    public static void insertSimple(Statement st) throws SQLException{
        int creaEquipo = st.executeUpdate("insert into equipos (nombre,ciudad,conferencia,division) values ('Los Folletis','Kagar','West','Central')");
    }
    public static void insertUpdatableResultSet(Statement st) throws SQLException{
        ResultSet rs = st.executeQuery("select nombre,ciudad,conferencia,division from equipos");
        rs.moveToInsertRow();
        rs.updateString("nombre", "Castratis");
        rs.updateString("ciudad", "Bastardo");
        rs.updateString("conferencia", "West");
        rs.updateString("division", "Atlantic");
        rs.insertRow();
    }
    public static void insertPreparedStatement(Connection conn) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into equipos (nombre,ciudad,conferencia,division) values (?,?,?,?)");
        ps.setString(1, "Tambaleantes");
        ps.setString(2, "Cargados Carajo");
        ps.setString(3, "East");
        ps.setString(4, "Pacific");
        ps.executeUpdate();
        ps.close();
    }
    public static void imprimir(Connection conn) throws SQLException {
       try(Statement statement=conn.createStatement();){
                    ResultSet rs = statement.executeQuery("select * from equipos");

        //rs.first();
        while (rs.next()) {
            System.out.println("Equipo: " 
                        +rs.getString("nombre") +"      Ciudad:"
                        +rs.getString("ciudad") +"      Conferencia:"
                        +rs.getString("conferencia") +"     Division:"
                        +rs.getString("division"));
        }
    }
    }
    public static void main(String[] args) {
        String urlConUsuario = "jdbc:mysql://localhost/NBA?user=root&password=1234";
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
          conn.setAutoCommit(false);
            System.out.println("\nSin cambios\n");
            imprimir(conn);
            System.out.println("\nActualizacion simple de 'Los Folletis'\n");
            
            insertSimple(statement);
            imprimir(conn);
            System.out.println("\nActualizacion simple de 'Castratis'\n");
            
            insertUpdatableResultSet(statement);
            imprimir(conn);
            
            System.out.println("\nActualizacion simple de 'Tambaleantes'\n");
            
            insertPreparedStatement(conn);
            imprimir(conn);
            
            conn.rollback();
        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }
    }
    
}
