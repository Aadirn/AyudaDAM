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
public abstract class DaoFactory {

    public static final int TIPO_MYSQL = 0;
    public static final int TIPO_FILE = 1;
    public static final int TIPO_MEMORY = 2;

    //Metodos que tienen que tener las factorias que devolvemos?
    public abstract ClienteDao getClienteDao()throws DaoException;

    public abstract EmpleadoDao getEmpleadoDao()throws DaoException;

    //Metodo que devuelve una factoria
    public static DaoFactory getFactory(int type) throws DaoException{
        switch (type) {
            case TIPO_MYSQL:
                return new MysqlDaoFactory();
            case TIPO_FILE:
                return new FileDaoFactory();
            case TIPO_MEMORY:
                return new MemoryDaoFactory();
            default:
                return null;
        }
    }

}
