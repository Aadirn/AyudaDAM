/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesabstractfactorypatternexample.factories;

import com.castelao.common.entities.figurasgeometricas.Cubo;
import com.castelao.common.entities.figurasgeometricas.Esfera;
import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;
import patronesabstractfactorypatternexample.Config;




/**
 *
 * @author castelao
 */
public class FiguraGeometrica3DFactory extends FiguraGeometricaFactory {

    public FiguraGeometrica3DFactory() {
    }

    @Override
    public FiguraGeometrica getCirculo() {
        return new Esfera(Config.getIntProperty("circulo.radio"),
                Config.getProperty("circulo.color"));
    }

    @Override
    public FiguraGeometrica getCuadrado() {
                return new Cubo(Config.getIntProperty("cuadrado.radio"),
                Config.getProperty("cuadrado.color"));
    }
    
}
