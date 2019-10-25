/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronvehiculo.factories;

import com.castelao.common.entities.vehiculos.BarcaRemos;
import com.castelao.common.entities.vehiculos.Fueraborda;
import com.castelao.common.entities.vehiculos.VehiculoBasic;

/**
 *
 * @author Castelao
 */
public class VehiculoAcuaticoFactory extends VehiculoFactory {

    public VehiculoAcuaticoFactory() {
    }

    @Override
    public VehiculoBasic getVSinMotor() {
        return new BarcaRemos();

    }

    @Override
    public VehiculoBasic getVConMotor() {
        return  new Fueraborda();
    }
}
