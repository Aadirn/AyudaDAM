/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.daos;

import java.util.List;
import patronesdaoandfactory.entities.Cliente;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public interface ClienteDao {
    void create(Cliente cliente) throws DaoException;

    Cliente read(int id) throws DaoException;

    void update(Cliente cliente) throws DaoException;

    void delete(Cliente cliente) throws DaoException;

    List<Cliente> findAll() throws DaoException;
}
