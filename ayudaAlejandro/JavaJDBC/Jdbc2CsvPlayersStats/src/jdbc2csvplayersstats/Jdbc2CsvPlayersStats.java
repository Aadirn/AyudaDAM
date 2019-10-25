/*
Obtener datos de jugadores de nba estaditicas por temporada, la más antigua primero crear fichero de extension .csv. Primero nombre de jugador, segundo el del equipo, tercero la ciudad.
 */
package jdbc2csvplayersstats;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Jdbc2CsvPlayersStats {

    private static Connection conn;

    private static List<String> obtenerDatosEstadisticas() throws SQLException {
        List<String> datosEstadisticas = new ArrayList<>();
        String linea;
        try (PreparedStatement estadisticas = conn.prepareStatement("select j.nombre,j.nombre_equipo,e.ciudad,es.puntos_por_partido,es.asistencias_por_partido,"
                + "es.tapones_por_partido,es.rebotes_por_partido ,if(substring(temporada,1,1)='9', concat('19',temporada),concat('20',temporada)) as temporada "
                + "from estadisticas es inner join jugadores j on j.codigo=es.jugador inner join equipos e on j.nombre_equipo=e.nombre order by temporada asc;");) {
            ResultSet rs = estadisticas.executeQuery();
            while (rs.next()) {
                linea = rs.getString("nombre").concat(";").concat(rs.getString("nombre_equipo").concat(";").concat(rs.getString("ciudad").concat(";").concat(rs.getString("puntos_por_partido")
                        .concat(";").concat(rs.getString("asistencias_por_partido").concat(";").concat(rs.getString("tapones_por_partido").concat(";")
                                .concat(rs.getString("rebotes_por_partido").concat(";").concat(rs.getString("temporada").concat("\n")))))))); 

                datosEstadisticas.add(linea);
            }
            rs.close();
        }
        return datosEstadisticas;
    }

    private static void generarFicheroCsv(List <String> lista,String path){
        //String [] listaDef;
        String putamierda;
        try(Writer escribidor = new FileWriter(path)){
            putamierda=lista.toString();
            escribidor.write(putamierda);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/nba";
        String user = "root";
        String pasword = "1234";
        String path ="c:\\temp\\joderaversiva.csv";
        try {
            conn = DriverManager.getConnection(url, user, pasword);
            
            generarFicheroCsv(obtenerDatosEstadisticas(), path);
            
            System.out.println("FICHERO CREADO!\n");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sql) {
                    System.err.println(sql.getMessage());
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
