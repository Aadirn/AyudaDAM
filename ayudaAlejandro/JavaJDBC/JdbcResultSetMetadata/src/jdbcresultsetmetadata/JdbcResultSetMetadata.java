/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcresultsetmetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Castelao
 */
public class JdbcResultSetMetadata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement();) {
            ResultSet metadataTest = statement.executeQuery("select * from partidos");
            System.out.println("getCatalogName");
            System.out.println(metadataTest.getMetaData().getCatalogName(2)+"\n");
            System.out.println("getColumnLabel");
            System.out.println(metadataTest.getMetaData().getColumnLabel(2)+"\n");
            System.out.println("getColumnDisplaySize");
            System.out.println(metadataTest.getMetaData().getColumnDisplaySize(2)+"\n");
            System.out.println("getColumnName");
            System.out.println(metadataTest.getMetaData().getColumnName(2)+"\n");
            System.out.println("getPrecision");
            System.out.println(metadataTest.getMetaData().getPrecision(2)+"\n");
            System.out.println("getColumnTypeName");
            System.out.println(metadataTest.getMetaData().getColumnTypeName(2)+"\n");
            System.out.println("getTableName");
            System.out.println(metadataTest.getMetaData().getTableName(2)+"\n");
            System.out.println("isAutoIncrement");
            System.out.println(metadataTest.getMetaData().isAutoIncrement(2)+"\n");
            System.out.println("isSearchable");
            System.out.println(metadataTest.getMetaData().isSearchable(2)+"\n");
            System.out.println("isWritable");
            System.out.println(metadataTest.getMetaData().isWritable(2)+"\n");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }

    }
}
