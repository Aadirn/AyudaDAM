/*
Cellstyle title =libro.create.cellstyle
 */
package jdbcexcelexport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * codigopedido,codigocliente,
 * fechapedido,fechaesperada,fechaentrega,importepedido
 */
public class JdbcExcelExport {

    private static Connection conn;

    public static void hojaPedidos(Workbook wb, String nombreHoja) throws SQLException {
        PreparedStatement pSt = conn.prepareStatement("select c.nombrecliente, sum(dp.cantidad*dp.preciounidad) as totalVentas from pedidos p "
                + "inner join detallepedidos dp on p.codigo=dp.codigopedido inner join clientes c on c.codigo=p.codigocliente group by c.nombrecliente order by c.nombrecliente");
        PreparedStatement pSt2 = conn.prepareStatement("SELECT p.codigo,p.codigocliente,p.fechapedido,p.fechaesperada,p.fechaentrega,SUM(dp.cantidad * dp.preciounidad) AS importe "
                + "FROM pedidos p JOIN detallepedidos dp ON p.codigo = dp.codigopedido GROUP BY p.codigo,p.codigocliente,p.fechapedido,p.fechaesperada,p.fechaentrega");
        ResultSet rs = pSt.executeQuery();
        ResultSet rs2 = pSt2.executeQuery();
        Sheet hojaNueva = wb.createSheet(nombreHoja);
        int rowNumber = 1;
        Row row = null;
        Cell cell;
        if ("Clientes".equals(nombreHoja)) {
            titulos(hojaNueva, new String[]{"NombreCliente","TotalVentas"});
            while (rs.next()) {
                row = hojaNueva.createRow(rowNumber++);

                cell = row.createCell(0);
                cell.setCellValue(rs.getString("nombrecliente"));

                cell = row.createCell(1);
                cell.setCellValue(rs.getString("totalVentas"));
            }
        } else {
            titulos(hojaNueva, new String[]{"Codigo","codigocliente","fechapedido","fechaesperada","fechaentrega","importe"});
            while (rs2.next()) {
                row = hojaNueva.createRow(rowNumber++);

                cell = row.createCell(0);
                cell.setCellValue(rs2.getInt("codigo"));

                cell = row.createCell(1);
                cell.setCellValue(rs2.getInt("codigocliente"));

                cell = row.createCell(2);
                cell.setCellValue(rs2.getString("fechapedido"));

                cell = row.createCell(3);
                cell.setCellValue(rs2.getString("fechaesperada"));

                cell = row.createCell(4);
                cell.setCellValue(rs2.getString("fechaentrega"));

                cell = row.createCell(5);
                cell.setCellValue(rs2.getFloat("importe"));
            }
        }
    }

    public static void titulos(Sheet hoja, String[] titulos) {
            Row row = hoja.createRow(0);;
            Cell cell;
            for (int i = 0; i < titulos.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(titulos[i]);
            }
        }

    

    public static void hojaDetallePedidos(Workbook wb, String estado) throws SQLException {
        PreparedStatement pSt2 = conn.prepareStatement("select p.estado,p.codigo,p.codigocliente,pr.nombre,dp.cantidad,dp.preciounidad from pedidos p "
                + "inner join detallepedidos dp on p.codigo=dp.codigopedido inner join productos pr on pr.codigo=dp.codigoproducto where p.estado=?");
        pSt2.setString(1, estado);
        ResultSet rs = pSt2.executeQuery();
        Sheet hojaNueva = wb.createSheet(estado);
        int rowNumber = 1;
        Row row = null;
        Cell cell;
        titulos(hojaNueva, new String[]{"codigo","codigocliente","nombre","cantidad","preciounidad"});
        
        while (rs.next()) {
            row = hojaNueva.createRow(rowNumber++);

            cell = row.createCell(0);
            cell.setCellValue(rs.getInt("codigo"));

            cell = row.createCell(1);
            cell.setCellValue(rs.getInt("codigocliente"));

            cell = row.createCell(2);
            cell.setCellValue(rs.getString("nombre"));

            cell = row.createCell(3);
            cell.setCellValue(rs.getInt("cantidad"));

            cell = row.createCell(4);
            cell.setCellValue(rs.getFloat("preciounidad"));
        }
    }

    public static void guardarDatos(Workbook wb, String path) throws FileNotFoundException, IOException {
        try (OutputStream stream = new FileOutputStream(path)) {
            wb.write(stream);
        }
        wb.close();
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/jardineria";
        String user = "root";
        String pasword = "1234";
        String path = "c:\\temp\\prueba.xlsx";
        String[] titulos={"nombrecliente","totalVentas","codigo",
            "codigocliente","fechapedido","fechaesperada","fechaentrega","importe","codigo","codigocliente","nombre","cantidad","preciounidad"};

        try {
            conn = DriverManager.getConnection(url, user, pasword);
            Workbook libroClientes = new XSSFWorkbook();
            System.out.println("Generando excel...\n\n\n");

            conn.setAutoCommit(false);

            hojaPedidos(libroClientes, "Clientes");
            hojaPedidos(libroClientes, "Pedidos");
            hojaDetallePedidos(libroClientes, "Entregado");
            hojaDetallePedidos(libroClientes, "Rechazado");
            hojaDetallePedidos(libroClientes, "Pendiente");
            

            System.out.println("Guardando excel...\n\n\n");

            guardarDatos(libroClientes, path);

            System.out.println("COMPLETADO!");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException sql) {
            System.err.println(sql.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqlex) {
                    System.err.println(sqlex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
