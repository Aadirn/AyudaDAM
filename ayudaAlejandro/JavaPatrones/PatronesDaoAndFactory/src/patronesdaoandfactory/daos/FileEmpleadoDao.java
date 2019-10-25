/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import patronesdaoandfactory.entities.Empleado;
import patronesdaoandfactory.exception.DaoException;

/**
 *
 * @author Castelao
 */
public class FileEmpleadoDao implements EmpleadoDao {

    private static final String CSV_SEPARATOR = ";";

    File file;

    public FileEmpleadoDao(File file) {
        this.file = file;
    }

    private int getNewId() throws IOException {

        int lineCount = 0;
        try (BufferedReader breader = new BufferedReader(new FileReader(file))) {

            while (breader.readLine() != null) {
                lineCount++;
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        return lineCount;
    }

    @Override
    public void create(Empleado empleado) throws DaoException {
        try (Writer writer = new FileWriter(file,true)) {
            writer.write(getNewId() + CSV_SEPARATOR + empleado.getDni() + CSV_SEPARATOR + empleado.getNombre()+"\n");
        } catch (IOException ex) {
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
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] fields = linea.split(CSV_SEPARATOR);
                empleados.add(new Empleado(Integer.valueOf(fields[0]), fields[1], fields[2]));
            }

        } catch (FileNotFoundException ex) {
            throw new DaoException(ex);
        } catch (IOException ex) {
            throw new DaoException(ex);
        }
        return empleados;
    }

}
