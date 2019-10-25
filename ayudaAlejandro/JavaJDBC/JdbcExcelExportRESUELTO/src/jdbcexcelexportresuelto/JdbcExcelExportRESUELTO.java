/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexcelexportresuelto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author castelao
 */
public class JdbcExcelExportRESUELTO {

    private static void hojaClientes(Connection conn, Workbook wb, CellStyle titleStyle) throws SQLException {

        try (Statement statement = conn.createStatement();
                ResultSet clientes = statement.executeQuery(
                        "select nombrecliente, sum(dp.cantidad*dp.preciounidad) as totalVentas "
                        + " from clientes c join pedidos p on c.codigo = p.codigocliente "
                        + " join detallepedidos dp on p.codigo = dp.codigopedido "
                        + "group by c.nombrecliente order by c.nombrecliente ");) {

            Sheet hojaClientes = wb.createSheet("Clientes");
            addTitles(hojaClientes, new String[]{"Cliente", "Total ventas"}, titleStyle);

            int rownumber = 1;
            Row row = null;
            Cell cell;

            while (clientes.next()) {

                row = hojaClientes.createRow(rownumber++);

                cell = row.createCell(0);
                cell.setCellValue(clientes.getString("nombrecliente"));

                cell = row.createCell(1);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(clientes.getFloat("totalVentas"));

            }

            row = hojaClientes.createRow(rownumber++);
            cell = row.createCell(1);

            String letraCol = CellReference.convertNumToColString(cell.getColumnIndex());
            String formula = String.format("SUM(%c%d:%c%d)", letraCol.charAt(0), 2, letraCol.charAt(0), rownumber - 1);
            cell.setCellFormula(formula);
        }
    }

    private static void hojaPedidos(Connection conn, Workbook wb, CellStyle titleStyle) throws SQLException {

        try (Statement statement = conn.createStatement();
                ResultSet clientes = statement.executeQuery(
                        "select p.codigo, p.codigocliente,fechaPedido,"
                        + "fechaEsperada,fechaEntrega, sum(dp.cantidad * dp.preciounidad) as importe "
                        + "from pedidos p join detallepedidos dp on p.codigo = dp.codigopedido "
                        + "group by p.codigo, p.codigocliente;");) {

            Sheet hojaClientes = wb.createSheet("Pedidos");
            addTitles(hojaClientes, new String[]{"Codigo", "Codigo Cliente", "Fecha pedido",
                "Fecha Esperada", "Fecha Entrega", "Importe"}, titleStyle);

            int rownumber = 1;
            Row row = null;
            Cell cell;

            while (clientes.next()) {

                row = hojaClientes.createRow(rownumber++);

                cell = row.createCell(0);
                cell.setCellValue(clientes.getInt("p.codigo"));
                cell = row.createCell(1);
                cell.setCellValue(clientes.getInt("p.codigoCliente"));
                cell = row.createCell(2);
                cell.setCellValue(clientes.getString("fechaPedido"));
                cell = row.createCell(3);
                cell.setCellValue(clientes.getString("fechaEsperada"));
                cell = row.createCell(4);
                cell.setCellValue(clientes.getString("fechaEntrega"));
                cell = row.createCell(0);
                cell.setCellValue(clientes.getString("importe"));

            }
        }
    }

    private static void hojaEstados(Connection conex, Workbook wb, String estado, CellStyle style) {
        try (PreparedStatement pstate = conex.prepareStatement("select p.codigo, p.codigocliente, pr.nombre, dp.cantidad, dp.preciounidad "
                + " from pedidos p inner join detallepedidos dp on p.codigo = dp.codigopedido "
                + " inner join productos pr on dp.codigoproducto = pr.codigo "
                + " where p.estado = ?")) {

            pstate.setString(1, estado);

            ResultSet rs = pstate.executeQuery();

            Sheet hojaEstados = wb.createSheet(estado);

            int rownumber = 1;
            Row row;
            Cell cell;

            while (rs.next()) {
                row = hojaEstados.createRow(rownumber++);

                cell = row.createCell(0);
                cell.setCellValue(rs.getInt("codigo"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getInt("codigoCliente"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString("Nombre"));
                cell = row.createCell(3);
                cell.setCellValue(rs.getInt("Cantidad"));
                cell = row.createCell(4);
                cell.setCellValue(rs.getInt("PrecioUnidad"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void addTitles(Sheet hoja, String[] titulos, CellStyle titleStyle) {
        Cell cell;
        Row row = hoja.createRow(0);
        for (int i = 0; i < titulos.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titulos[i]);
            cell.setCellStyle(titleStyle);
        }
    }

    private static void writeToFile(Workbook libro, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            libro.write(fos);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria", "root", "1234")) {

            Workbook libroClientes = new XSSFWorkbook();

            CellStyle titleStyle = libroClientes.createCellStyle();
            titleStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            System.out.println("Generando hoja de clientes...");
            hojaClientes(conn, libroClientes, titleStyle);

            hojaPedidos(conn, libroClientes, titleStyle);

            // 3 Formas de llamar al método
            /*   1
            hojaEstados(conn, libroClientes, "Entregado", titleStyle);
            hojaEstados(conn, libroClientes, "Pendiente", titleStyle);
            hojaEstados(conn, libroClientes, "Rechazado", titleStyle);
             */
            //    2
            String[] estados = {"Entregado", "Pendiente", "Rechazado"};
            for (String est : estados) {
                hojaEstados(conn, libroClientes, est, titleStyle);
            }
            /* 3
            List<String> estadosList = Arrays.asList(estados);
            for (String est : estadosList) {
                hojaEstados(conn, libroClientes, est, titleStyle);
            }
             */
            writeToFile(libroClientes, "c:\\temp\\clientes.xls");

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
