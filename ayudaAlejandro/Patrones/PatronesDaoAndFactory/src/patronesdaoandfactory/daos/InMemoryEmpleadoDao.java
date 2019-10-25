/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.daos;

import java.util.ArrayList;
import java.util.List;
import patronesdaoandfactory.entities.Empleado;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class InMemoryEmpleadoDao implements EmpleadoDao{

   List<Empleado> empleados=new ArrayList<>();
   
    
    
    
    @Override
    public void create(Empleado empleado) throws DaoException {
        empleado.setId(empleados.size());
        empleados.add(empleado);
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
        return empleados;
    }
    
}
