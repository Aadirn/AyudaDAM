/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import patronesdaoandfactory.entities.Empleado;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class MysqlEmpleadoDao implements EmpleadoDao {

    private static final String SQL_EMPLEADO_INSERT = "insert into empleados(dni,nombre) values(?,?)";

    private static final String SQL_EMPLEADO_FINDALL = "select id,dni,nombre from empleados";

    private Connection conn;

    public MysqlEmpleadoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Empleado empleado) throws DaoException {
        try (PreparedStatement pst = conn.prepareStatement(SQL_EMPLEADO_INSERT)) {
            pst.setString(1, empleado.getDni());
            pst.setString(2, empleado.getNombre());

            pst.executeUpdate();
        } catch (SQLException ex) {
           throw new DaoException(ex);
        }
    }

    @Override
    public Empleado read(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Empleado empleado) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Empleado empleado) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> findAll() throws DaoException {
        List<Empleado> empleados = new ArrayList();
        try (PreparedStatement pst = conn.prepareStatement(SQL_EMPLEADO_FINDALL)) {
            ResultSet empsRs = pst.executeQuery();

            while (empsRs.next()) {
                empleados.add(new Empleado(empsRs.getInt("id"), empsRs.getString("dni"), empsRs.getString("nombre")));
            }

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return empleados;
    }

}
