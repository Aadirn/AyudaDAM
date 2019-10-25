/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesstaticfactorypatternexample.factories;

import com.castelao.common.entities.figurasgeometricas.Circulo;
import com.castelao.common.entities.figurasgeometricas.Cuadrado;
import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;
import patronesstaticfactorypatternexample.Config;



/**
 *
 * @author castelao
 */
public class FiguraGeometricaFactory {

    public static final int TIPO_CIRCULO = 1;
    public static final int TIPO_CUADRADO = 2;

    public static FiguraGeometrica getFiguraGeometrica(int type) {

        switch (type) {
            case TIPO_CIRCULO:
                return new Circulo(12312, "azul", "");
            case TIPO_CUADRADO:
                return new Cuadrado(Config.getIntProperty("cuadrado.radio"),
                        Config.getProperty("cuadrado.color"));
            default:
                return null;
        }
    }
}
