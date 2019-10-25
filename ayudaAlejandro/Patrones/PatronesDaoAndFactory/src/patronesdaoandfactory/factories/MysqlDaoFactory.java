/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import patronesdaoandfactory.daos.ClienteDao;
import patronesdaoandfactory.daos.EmpleadoDao;
import patronesdaoandfactory.daos.MysqlClienteDao;
import patronesdaoandfactory.daos.MysqlEmpleadoDao;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class MysqlDaoFactory extends DaoFactory {
    
    private static final String PATH = "c:\\temp\\empleados.csv";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/castelao";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    
    private Connection conn;

    public MysqlDaoFactory() {
        
    }
    
    private void initConnection() throws DaoException{
        if(conn==null){
            try{
                conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            }catch(SQLException ex){
                throw new DaoException(ex);
            }
        }
    }

    
    @Override
    public ClienteDao getClienteDao() throws DaoException{
        initConnection();
        return new MysqlClienteDao(conn);
    }

    @Override
    public EmpleadoDao getEmpleadoDao() throws DaoException{
        initConnection();
        return new MysqlEmpleadoDao(conn);
    }
    
    
    
}
