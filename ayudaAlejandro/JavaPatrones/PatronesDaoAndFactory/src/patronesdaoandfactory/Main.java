/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import patronesdaoandfactory.daos.EmpleadoDao;
import patronesdaoandfactory.factories.EmpleadoDaoStaticFactory;
import patronesdaoandfactory.daos.FileEmpleadoDao;
import patronesdaoandfactory.daos.InMemoryEmpleadoDao;
import patronesdaoandfactory.daos.MysqlEmpleadoDao;
import patronesdaoandfactory.entities.Empleado;
import patronesdaoandfactory.exception.DaoException;
import patronesdaoandfactory.factories.DaoFactory;

/**
 *
 * @author Castelao
 */
public class Main {

    private static final String PATH = "c:\\temp\\empleados.csv";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/castelao";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    private static final String target = "DB";

    public static void main(String[] args) {

        Empleado ernesto = new Empleado("111", "Ernesto");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            //Creamos un DAO para MySQL
            //EmpleadoDao empleadoDao = new MysqlEmpleadoDao(conn);
            //EmpleadoDao empleadoDao = new FileEmpleadoDao(new File(PATH));
            //EmpleadoDao empleadoDao = new InMemoryEmpleadoDao();
            EmpleadoDao empleadoDao = null;
            switch (target) {
                case "DB":
                    empleadoDao = new MysqlEmpleadoDao(conn);
                    break;
                case "FILE":
                    empleadoDao = new FileEmpleadoDao(new File(PATH));
                    break;
                case "MEMORY":
                    empleadoDao = new InMemoryEmpleadoDao();
                    break;
                /*case "DB":
                    empleadoDao= new MysqlEmpleadoDao(conn);
                    break;*/
                default:
                    empleadoDao = null;
            }

            empleadoDao = EmpleadoDaoStaticFactory.getFactory(EmpleadoDaoStaticFactory.TIPO_FILE);

            DaoFactory daoFactory = DaoFactory.getFactory(DaoFactory.TIPO_FILE);
            Empleado amancio = new Empleado("333", "Amancio");
            try {

                empleadoDao = daoFactory.getEmpleadoDao();
                empleadoDao.create(amancio);
                

                List<Empleado> empleados = empleadoDao.findAll();

                System.out.println("Lista de empleados: ");
                for (Empleado emp : empleados) {
                    System.out.println(emp);
                }

            } catch (DaoException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (DaoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
