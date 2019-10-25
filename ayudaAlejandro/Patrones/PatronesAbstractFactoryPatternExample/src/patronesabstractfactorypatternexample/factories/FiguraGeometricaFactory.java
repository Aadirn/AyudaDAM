/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesabstractfactorypatternexample.factories;

import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;


/**
 *
 * @author castelao
 */
public abstract class FiguraGeometricaFactory {
    
    // Métodos que tienen que implementar las factorías
    public abstract FiguraGeometrica getCirculo();
    public abstract FiguraGeometrica getCuadrado();
    
    // Método que devuelve una factoría
    public static FiguraGeometricaFactory getFactory(boolean modo3d) {
        if (modo3d) {
            return new FiguraGeometrica3DFactory();
        } else {
            return new FiguraGeometrica2DFactory();
        }
    }
}
