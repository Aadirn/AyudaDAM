
package patronvehiculo.factories;

import com.castelao.common.entities.vehiculos.VehiculoBasic;

public abstract class VehiculoFactory {
    
    public abstract VehiculoBasic getVSinMotor();
    public abstract VehiculoBasic getVConMotor();
    
    public static VehiculoFactory getFactory(boolean terrestre){
        if(terrestre){
            return new VehiculoTerrestreFactory();
        }else{
            return new VehiculoAcuaticoFactory();
        }
    }
}
