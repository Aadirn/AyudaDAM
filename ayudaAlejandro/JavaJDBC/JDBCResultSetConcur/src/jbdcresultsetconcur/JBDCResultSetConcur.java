/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdcresultsetconcur;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*mostrar por pantalla los tipos de resultset y tipos de concurrencia soportados en mysql*/
public class JBDCResultSetConcur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/NBA";
        String user = "root";
        String password = "1234";
        try(Connection conn=DriverManager.getConnection(urlSinUsuario, user, password);){
        DatabaseMetaData metadata = conn.getMetaData();
        //metadata.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        System.out.println("Forward_Only - Read_Only:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));
        System.out.println("Forward_Only - Updatable:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
        System.out.println("Scroll_Insensitive - Read_Only:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY));
        System.out.println("Scroll_Insensitive - Updatable:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
        System.out.println("Scroll_Sensitive - Read_Only:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
        System.out.println("Scroll_Sensitive - Updatable:"+metadata.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));
        
        }catch(SQLException sql){
            System.err.println(sql.getMessage());
        }

    }
}
