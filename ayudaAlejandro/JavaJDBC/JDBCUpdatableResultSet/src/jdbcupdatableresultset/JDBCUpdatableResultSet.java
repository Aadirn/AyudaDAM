/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcupdatableresultset;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUpdatableResultSet {

    public static void imprimir(ResultSet rs) throws SQLException {
        rs.first();
        while (rs.next()) {
            System.out.println(rs.getInt("codigo") + "\tNombre Cliente:"
                    + rs.getString("nombrecliente") + "\tTelefono:"
                    + rs.getString("telefono") + "\tFax:"
                    + rs.getString("fax") + "\tDireccion:"
                    + rs.getString("lineadireccion1") + "\tCiudad:"
                    + rs.getString("ciudad") + "\tRegion:"
                    + rs.getString("region") + "\tPais:"
                    + rs.getString("pais") + "\tCodigo Postal:"
                    + rs.getString("codigopostal") + "\tLimite Credito:"
                    + rs.getBigDecimal("limitecredito"));
        }
    }

    public static void main(String[] args) {
        String urlSinUsuario = "jdbc:mysql://localhost/jardineria";
        String user = "root";
        String password = "1234";
        try (Connection conn = DriverManager.getConnection(urlSinUsuario, user, password);
                Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
            conn.setAutoCommit(false);
            ResultSet jardineriaRs = statement.executeQuery("select codigo, nombrecliente,telefono,fax,lineadireccion1,ciudad,region,pais,codigopostal,limitecredito "
                    + "from clientes order by codigo asc");
            imprimir(jardineriaRs);

            System.out.println("\nAÑADIMOS LA FILA CON LOS VALORES QUE QUEREMOS\n");

            jardineriaRs.moveToInsertRow();
            jardineriaRs.updateInt("codigo", 40);
            jardineriaRs.updateString("nombrecliente", "PAPARRUCHAS S.A");
            jardineriaRs.updateString("telefono", "688547523");
            jardineriaRs.updateString("fax", "688536523");
            jardineriaRs.updateString("lineadireccion1", "Calle Falsa Nº123");
            jardineriaRs.updateString("ciudad", "SpringField");
            jardineriaRs.updateString("region", "Nevada");
            jardineriaRs.updateString("pais", "Rusia");
            jardineriaRs.updateString("codigopostal", "50002");
            jardineriaRs.updateBigDecimal("limitecredito", new BigDecimal(50000.00));
            jardineriaRs.insertRow();
            imprimir(jardineriaRs);
            System.out.println("\nACTUALIZAMOS LOS CAMPOS QUE QUERAMOS DE LA FILA CON LOS VALORES NUEVOS\n");
            jardineriaRs.last();
            jardineriaRs.updateString("telefono", "666666666");
            jardineriaRs.updateBigDecimal("limitecredito", new BigDecimal(69420.69));
            jardineriaRs.updateRow();
            imprimir(jardineriaRs);

            System.out.println("\nBORRAMOS LA FILA\n");
            jardineriaRs.last();
            jardineriaRs.deleteRow();
            imprimir(jardineriaRs);
            conn.rollback();

        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
        }

    }
}
