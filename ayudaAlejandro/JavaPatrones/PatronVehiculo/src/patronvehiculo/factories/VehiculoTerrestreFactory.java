/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronvehiculo.factories;

import com.castelao.common.entities.vehiculos.Bicicleta;
import com.castelao.common.entities.vehiculos.Coche;
import com.castelao.common.entities.vehiculos.VehiculoBasic;

/**
 *
 * @author Castelao
 */
public class VehiculoTerrestreFactory extends VehiculoFactory {

    public VehiculoTerrestreFactory() {
    }
    
    @Override
    public VehiculoBasic getVSinMotor(){
        return new Bicicleta();
        
    }
    
    @Override
    public VehiculoBasic getVConMotor(){
        return new Coche();
    }
}
