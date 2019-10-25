/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.factories;

import patronesdaoandfactory.daos.ClienteDao;
import patronesdaoandfactory.daos.EmpleadoDao;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class MemoryDaoFactory extends DaoFactory {

    public MemoryDaoFactory() {
    }

    @Override
    public ClienteDao getClienteDao() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoDao getEmpleadoDao() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
