/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesdaoandfactory.factories;

import java.io.File;
import java.sql.Connection;
import patronesdaoandfactory.daos.EmpleadoDao;
import patronesdaoandfactory.daos.FileEmpleadoDao;
import patronesdaoandfactory.daos.InMemoryEmpleadoDao;
import patronesdaoandfactory.daos.MysqlEmpleadoDao;


public class EmpleadoDaoStaticFactory {
    
    public static final int TIPO_MYSQL = 0;
    public static final int TIPO_FILE = 1;
    public static final int TIPO_MEMORY = 2;
    
    private static Connection conn;
    
     private static final String PATH = "c:\\temp\\empleados.csv";
    
    public static EmpleadoDao getFactory(int type){
        switch (type) {
                case TIPO_MYSQL:
                    return new MysqlEmpleadoDao(conn);
                    
                case TIPO_FILE:
                    return new FileEmpleadoDao(new File(PATH));
                    
                case TIPO_MEMORY:
                    return new InMemoryEmpleadoDao();
                   
                /*case "DB":
                    empleadoDao= new MysqlEmpleadoDao(conn);
                    break;*/
                default: return null;
            }
        
    }
    
    private void initConnection(){
        //Inicializar la conexion...
    }
    
}
