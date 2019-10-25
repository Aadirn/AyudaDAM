/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.daos;

import java.sql.Connection;
import java.util.List;
import patronesdaoandfactory.entities.Cliente;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class MysqlClienteDao implements ClienteDao{
    
    private Connection conn;
    
    public MysqlClienteDao(Connection conn){
        this.conn=conn;
    }

    @Override
    public void create(Cliente cliente) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente read(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente cliente) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cliente cliente) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> findAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
