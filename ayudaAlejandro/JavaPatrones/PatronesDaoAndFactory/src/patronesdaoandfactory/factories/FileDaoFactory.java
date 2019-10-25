/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.factories;

import java.io.File;
import patronesdaoandfactory.daos.ClienteDao;
import patronesdaoandfactory.daos.EmpleadoDao;
import patronesdaoandfactory.daos.FileEmpleadoDao;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class FileDaoFactory extends DaoFactory {
    
    private static final String EMPLEADOS_PATH="c:\\temp\\empleados.csv";

    public FileDaoFactory() {
    }


    @Override
    public ClienteDao getClienteDao() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoDao getEmpleadoDao() throws DaoException {
        return new FileEmpleadoDao(new File(EMPLEADOS_PATH));
    }
    
}
