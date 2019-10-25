/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * try(statement st = conn.createstatement()){ commit }catch(){ }rollback catch
 * de todo
 */
public class JDBCTransaction {

    public static void imprimir(ResultSet rs) throws SQLException {
        //rs.first();
        while (rs.next()) {
            System.out.println(rs.getString("codigo") + "\tCiudad:"
                    + rs.getString("ciudad") + "\tPais:"
                    + rs.getString("pais") + "\tRegion:"
                    + rs.getString("region") + "\tCodigo Postal:"
                    + rs.getString("codigopostal") + "\tTelefono:"
                    + rs.getString("telefono") + "\tDireccion 1:"
                    + rs.getString("lineadireccion1"));
        }
    }

    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/jardineria";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
            conn.setAutoCommit(false);
            ResultSet jardineriaRs = statement.executeQuery("select * from oficinas");
            try (Statement rb = conn.createStatement()) {
                jardineriaRs.moveToInsertRow();
                jardineriaRs.updateString("codigo", "AC-ES");
                jardineriaRs.updateString("ciudad", "A Corunha");
                jardineriaRs.updateString("pais", "Espanha");
                jardineriaRs.updateString("region", "Galicia");
                jardineriaRs.updateString("codigopostal", "18602");
                jardineriaRs.updateString("telefono", "+34 952 21 47 54");
                jardineriaRs.updateString("lineadireccion1", "Calle Ataulfo Salchonio Nº69");
                jardineriaRs.insertRow();
                jardineriaRs.first();
                imprimir(jardineriaRs);
                System.out.println("\n*****************************************************\n");
                conn.rollback();
                jardineriaRs.beforeFirst();
                imprimir(jardineriaRs);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
