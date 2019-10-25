/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesabstractfactorypatternexample.factories;

import com.castelao.common.entities.figurasgeometricas.Circulo;
import com.castelao.common.entities.figurasgeometricas.Cuadrado;
import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;
import patronesabstractfactorypatternexample.Config;



/**
 *
 * @author castelao
 */
public class FiguraGeometrica2DFactory extends FiguraGeometricaFactory {

    public FiguraGeometrica2DFactory() {
    }

    @Override
    public FiguraGeometrica getCirculo() {
        return new Circulo(Config.getIntProperty("circulo.radio"),
                Config.getProperty("circulo.color"),
                Config.getProperty("circulo.color.background"));
    }

    @Override
    public FiguraGeometrica getCuadrado() {
        return new Cuadrado(Config.getIntProperty("cuadrado.radio"),
                Config.getProperty("cuadrado.color"));
    }

}
