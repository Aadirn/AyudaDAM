/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcinformationschema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JdbcInformationSchema {

    private static Connection conn;
    
    private static List<String> obtenerListaColumnas(String bdName,String tableName){
        List<String> cols = new ArrayList<>();
        try(PreparedStatement listaColsSt = conn.prepareStatement("select column_name, column_type from columns where table_schema = ? and table_name=?")){
            
            listaColsSt.setString(1, bdName);
            listaColsSt.setString(2, tableName);
            ResultSet listaColsRs = listaColsSt.executeQuery();
            while(listaColsRs.next()){
                cols.add(listaColsRs.getString("column_name"+"\t"+listaColsRs.getString("column_type")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return cols;
    }
    
    private static String generarCreateTable(String bdName,String tableName){
        String createTable = "CREATE TABLE"+tableName+"(";
        List<String> cols = obtenerListaColumnas(bdName,tableName);
        Iterator<String> iter = cols.iterator();
        while(iter.hasNext()){
            createTable=createTable.concat("\n"+iter.next());
            if(iter.hasNext()){
                createTable+=", ";
            }
        }
        createTable+=");\n\n";
        return createTable;
    }
    
    private static List<String>obtenerListaTablas(String bdName){
        List<String> tablas = new ArrayList<>();
        try(PreparedStatement listaTablasSt = conn.prepareStatement("select table_name from tables where table_schema = ?")){
            
            listaTablasSt.setString(1, bdName);
            ResultSet listaTablaRs=listaTablasSt.executeQuery();
            while(listaTablaRs.next()){
                tablas.add(listaTablaRs.getString("table_name"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tablas;
    }
    
    private static void guardarEnDisco(String bdName,String scriptSql){
        try(Writer escribidor = new FileWriter("c:\\temp\\"+bdName+".sql")){
            
            escribidor.write(scriptSql);
            
    }   catch (IOException ex) {
            System.err.println(ex.getMessage());
    }
        }
        
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/information_schema";
        String user = "root";
        String password = "1234";
        String bdName="jardineria";
        try {
            conn = DriverManager.getConnection(url, user, password);
            List<String> tablas = obtenerListaTablas(bdName);
            
            StringBuilder scriptSql = new StringBuilder();
            
            for(String t:tablas){
                scriptSql.append(generarCreateTable(bdName, t));
            }
            guardarEnDisco(bdName, scriptSql.toString());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqlex) {
                    System.err.println(sqlex.getMessage());
                }
            }

        } finally {
            if (conn != null) {
                try{
                conn.close();
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
