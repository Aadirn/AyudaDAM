
package jdbcplayerbatchupdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class JdbcPlayerBatchUpdate {

    private static Connection conn;

    private static void updateOnline() throws SQLException {
        conn.setAutoCommit(false);
        int cod=614;
         PreparedStatement insertar = conn.prepareStatement("insert into jugadores"
                + "(codigo,nombre) values (?,?)");  
         for(int i =0;i<20000;i++){

        insertar.setInt(1, cod);
        insertar.setString(2, "UpdateOnline");
        insertar.executeUpdate();
        cod++;
        }
        conn.rollback();
    }

    private static void updateBatch() throws SQLException {
        conn.setAutoCommit(false);
        int cod=614;
        PreparedStatement insertar = conn.prepareStatement("insert into jugadores"
                + "(codigo,nombre) values (?,?)"); 
        for(int i =0;i<20000;i++){

        insertar.setInt(1, cod);
        insertar.setString(2, "UpdateBatch");
        insertar.addBatch();
        cod++;
        } 
        insertar.executeBatch();

        conn.rollback();
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/nba";
        String user = "root";
        String pasword = "1234";
        try {
            conn = DriverManager.getConnection(url, user, pasword);
            Date start,end;
            
            start=new Date();
            updateOnline();
            end=new Date();
            System.out.println("Tiempo (ms) del updateOnline: "+(end.getTime()-start.getTime()));
            
            start=new Date();
            updateBatch();
            end=new Date();
            System.out.println("Tiempo (ms) del updateBatch: "+(end.getTime()-start.getTime()));
            
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
