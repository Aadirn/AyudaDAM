/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronvehiculo;

import com.castelao.common.entities.vehiculos.BarcaRemos;
import com.castelao.common.entities.vehiculos.Coche;
import com.castelao.common.entities.vehiculos.VehiculoBasic;
import patronvehiculo.factories.VehiculoFactory;

/**
 *
 * @author Castelao
 */
public class PatronVehiculo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        VehiculoFactory fabricaVTerrestres = VehiculoFactory.getFactory(true);
        
        VehiculoBasic coche = fabricaVTerrestres.getVConMotor();
        
        System.out.println("Es un coche? "+(coche instanceof Coche));
        
        VehiculoFactory fabricaVMarinos = VehiculoFactory.getFactory(false);
        
        VehiculoBasic barcaRemos = fabricaVMarinos.getVSinMotor();
        
        System.out.println("Es una barca? "+(barcaRemos instanceof BarcaRemos));
    }
    
}
