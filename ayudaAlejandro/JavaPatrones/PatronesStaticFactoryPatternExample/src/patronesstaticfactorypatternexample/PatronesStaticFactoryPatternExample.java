/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesstaticfactorypatternexample;

import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;
import patronesstaticfactorypatternexample.factories.FiguraGeometricaFactory;



/**
 *
 * @author castelao
 */
public class PatronesStaticFactoryPatternExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Config.getProperty("cuadrado.color"));

        FiguraGeometrica circulo
         = FiguraGeometricaFactory.getFiguraGeometrica(FiguraGeometricaFactory.TIPO_CIRCULO);

        FiguraGeometrica cuadrado
                = FiguraGeometricaFactory.getFiguraGeometrica(FiguraGeometricaFactory.TIPO_CUADRADO);
    }

}
