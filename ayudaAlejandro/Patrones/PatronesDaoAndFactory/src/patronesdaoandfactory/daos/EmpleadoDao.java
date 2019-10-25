package patronesdaoandfactory.daos;

import java.util.List;
import patronesdaoandfactory.entities.Empleado;
import patronesdaoandfactory.exception.DaoException;

public interface EmpleadoDao {

    void create(Empleado empleado) throws DaoException;

    Empleado read(int id) throws DaoException;

    void update(Empleado empleado) throws DaoException;

    void delete(Empleado empleado) throws DaoException;

    List<Empleado> findAll() throws DaoException;

}
