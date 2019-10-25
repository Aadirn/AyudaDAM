/**/
package jdbcallstars;


import com.castelao.common.utils.GeneradoresUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcAllStars {

    private Connection conn;
    ;
/*
    Forma un equipo con los mejores jugadores . Hay que escoger
    los 2 mejores jugadores por posición (pero no hay una lista fija de posiciones preestablecida,
    pueden aparecer nuevas posiciones), utiliza colas con prioridad para ir ordenando los jugadores.
     */
    private final int PUNTOS_POR_PUNTO = 4;
    private final int PUNTOS_POR_ASISTENCIA = 3;
    private final int PUNTOS_POR_REBOTE = 2;
    private final int PUNTOS_POR_TAPON = 1;

    private final int PUNTOS_MEJOR_ENCESTADOR_TEMP = 200;
    private final int PUNTOS_MEJOR_ASISTENTE_TEMP = 100;
    private final int PUNTOS_MEJOR_REBOTEADOR_TEMP = 50;
    private final int PUNTOS_MEJOR_TAPONEADOR_TEMP = 30;

    private final int PUNTOS_ENCESTADOR_HIS = 400;
    private final int PUNTOS_ASISTENTE_HIS = 300;
    private final int PUNTOS_REBOTEADOR_HIS = 200;
    private final int PUNTOS_TAPONEADOR_HIS = 100;

    private Map<Integer, Player> playersMap;
    private List<String> temporadas;
    private PriorityQueue<Player> bestEquipo;

    public JdbcAllStars() {
        playersMap = new HashMap<>();
        temporadas = new ArrayList<>();
        bestEquipo = new PriorityQueue<>();
    }

    private void initDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/nba", "root", "1234");
    }


    private Map<String, PriorityQueue<Player>> colasPorPosicion() {
        /*recorrer jugadores
        para cada jugador
        si no existe PQ
        crear la PQ para esa posicion
        fin
        añadir a la PQ */

        Map<String, PriorityQueue<Player>> posicionesMap = new HashMap<>();
        Iterator iter = playersMap.keySet().iterator();

        while (iter.hasNext()) {
            Player player = playersMap.get(iter.next());
            PriorityQueue<Player> posNueva = posicionesMap.get(player.getPosicion());
            if (posNueva == null) {
                posNueva = new PriorityQueue<>();
                posicionesMap.put(player.getPosicion(), posNueva);
            }
            posNueva.add(player);
        }
        return posicionesMap;
    }

    private List<Player> mejorDeMejores() {
        List<Player> allStars = new ArrayList<>();
        Map<String, PriorityQueue<Player>> posicionesMap = colasPorPosicion();
        for (String posicion : posicionesMap.keySet()) {

            PriorityQueue<Player> pq = posicionesMap.get(posicion);

            allStars.add(pq.remove());
            allStars.add(pq.remove());
        }
        return allStars;
    }

    private void showAllStars(List<Player> players) {
        for (Player p : players) {
            System.out.println(p.getPosicion() + " " + p.getPuntuacion() + " " + p.getNombre());
        }
    }

    private float puntuacionAllStars(float puntos, float asistencias, float rebotes, float tapones) {
        return (puntos * PUNTOS_POR_PUNTO) + (asistencias * PUNTOS_POR_ASISTENCIA) + (rebotes * PUNTOS_POR_REBOTE) + (tapones * PUNTOS_POR_TAPON);
    }

    private void obtenerDatosAllStars() {
        try (PreparedStatement jugadores = conn.prepareStatement("select j.codigo,j.nombre,j.posicion,sum(es.puntos_por_partido) puntos,"
                + "sum(es.asistencias_por_partido) asistencias, sum(es.tapones_por_partido) tapones, sum(es.rebotes_por_partido) rebotes "
                + "from jugadores j inner join estadisticas es on j.codigo=es.jugador group by j.codigo,j.nombre,j.posicion");) {
            ResultSet rs = jugadores.executeQuery();
            while (rs.next()) {
                playersMap.put(rs.getInt("codigo"), new Player(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("posicion"),
                        puntuacionAllStars(rs.getFloat("puntos"), rs.getFloat("asistencias"),
                                rs.getFloat("tapones"), rs.getFloat("rebotes")) + getPuntacionPeriodistas() + getPuntuacionFans()));

            }

        } catch (SQLException ex) {
            System.err.println("Fallo en Obtener Datos " + ex.getMessage());
        }
    }

    private void loadTemporadas() {
        try (PreparedStatement pSt = conn.prepareStatement("select distinct temporada from estadisticas");) {
            ResultSet rs = pSt.executeQuery();
            while (rs.next()) {
                temporadas.add(rs.getString("temporada"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al cargar las temporadas" + ex.getMessage());
        }
    }

    private void actualizarMejoresAnotadoresTemporada() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where temporada =? and puntos_por_partido="
                + "(select max(puntos_por_partido) from estadisticas where temporada = ?)");) {
            float puntActualizar = 0;
            for (String temporada : temporadas) {
                pst.setString(1, temporada);
                pst.setString(2, temporada);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Player player = playersMap.get(rs.getInt("jugador"));
                    player.setPuntuacion(player.getPuntuacion() + PUNTOS_MEJOR_ENCESTADOR_TEMP);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar TEMPORADA: " + ex.getMessage());
        }
    }

    private void actualizarMejoresAsistentesTemporada() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where temporada =? and asistencias_por_partido="
                + "(select max(asistencias_por_partido) from estadisticas where temporada = ?)");) {
            float puntActualizar = 0;
            for (String temporada : temporadas) {
                pst.setString(1, temporada);
                pst.setString(2, temporada);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Player player = playersMap.get(rs.getInt("jugador"));
                    player.setPuntuacion(player.getPuntuacion() + PUNTOS_MEJOR_ASISTENTE_TEMP);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar TEMPORADA: " + ex.getMessage());
        }
    }

    private void actualizarMejoresTaponeadoresTemporada() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where temporada =? and tapones_por_partido="
                + "(select max(tapones_por_partido) from estadisticas where temporada = ?)");) {
            float puntActualizar = 0;
            for (String temporada : temporadas) {
                pst.setString(1, temporada);
                pst.setString(2, temporada);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Player player = playersMap.get(rs.getInt("jugador"));
                    player.setPuntuacion(player.getPuntuacion() + PUNTOS_MEJOR_TAPONEADOR_TEMP);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar TEMPORADA: " + ex.getMessage());
        }
    }

    private void actualizarMejoresReboteadoresTemporada() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where temporada =? and rebotes_por_partido="
                + "(select max(rebotes_por_partido) from estadisticas where temporada = ?)");) {
            for (String temporada : temporadas) {
                pst.setString(1, temporada);
                pst.setString(2, temporada);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Player player = playersMap.get(rs.getInt("jugador"));
                    player.setPuntuacion(player.getPuntuacion() + PUNTOS_MEJOR_REBOTEADOR_TEMP);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar TEMPORADA: " + ex.getMessage());
        }
    }

    private void actualizarAnotadorHistorico() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where puntos_por_partido=(select max(puntos_por_partido) from estadisticas)")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Player player = playersMap.get(rs.getInt("jugador"));
                player.setPuntuacion(player.getPuntuacion() + PUNTOS_ENCESTADOR_HIS);
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar HISTORICO" + ex.getMessage());
        }
    }

    private void actualizarAsistenteHistorico() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where asistencias_por_partido=(select max(asistencias_por_partido) from estadisticas)")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Player player = playersMap.get(rs.getInt("jugador"));
                player.setPuntuacion(player.getPuntuacion() + PUNTOS_ASISTENTE_HIS);
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar HISTORICO" + ex.getMessage());
        }
    }

    private void actualizarTaponeadorHistorico() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where tapones_por_partido=(select max(tapones_por_partido) from estadisticas)")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Player player = playersMap.get(rs.getInt("jugador"));
                player.setPuntuacion(player.getPuntuacion() + PUNTOS_TAPONEADOR_HIS);
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar HISTORICO" + ex.getMessage());
        }
    }

    private void actualizarReboteadorHistorico() {
        try (PreparedStatement pst = conn.prepareStatement("select jugador from estadisticas where rebotes_por_partido=(select max(rebotes_por_partido) from estadisticas)")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Player player = playersMap.get(rs.getInt("jugador"));
                player.setPuntuacion(player.getPuntuacion() + PUNTOS_REBOTEADOR_HIS);
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar HISTORICO" + ex.getMessage());
        }
    }

    private void actualizarTodo() {
        loadTemporadas();
        actualizarMejoresAnotadoresTemporada();
        actualizarMejoresAsistentesTemporada();
        actualizarMejoresReboteadoresTemporada();
        actualizarMejoresTaponeadoresTemporada();
        actualizarAnotadorHistorico();
        actualizarAsistenteHistorico();
        actualizarReboteadorHistorico();
        actualizarTaponeadorHistorico();
    }

    private void imprimir() {
        System.out.println(playersMap);
    }

    private int getPuntacionPeriodistas() {
        int[] puntFinal;
        puntFinal = GeneradoresUtil.enterosAleatorios(1, 1, 500);
        return puntFinal[0];
    }

    private int getPuntuacionFans() {
        int[] puntFinal;
        puntFinal = GeneradoresUtil.enterosAleatorios(1, 1, 5000);
        return puntFinal[0];
    }

    public static void main(String[] args) {
        JdbcAllStars allStars = new JdbcAllStars();
        try {
            allStars.initDb();

            allStars.obtenerDatosAllStars();
            allStars.actualizarTodo();

            System.out.println("\nAhora se mostrara el equipo de AllStars: \n");

            allStars.showAllStars(allStars.mejorDeMejores());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (allStars != null) {
            }
            try {
                allStars.conn.close();

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }
}
