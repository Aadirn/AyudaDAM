/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesabstractfactorypatternexample;

import com.castelao.common.entities.figurasgeometricas.Circulo;
import com.castelao.common.entities.figurasgeometricas.Esfera;
import com.castelao.common.entities.figurasgeometricas.FiguraGeometrica;
import patronesabstractfactorypatternexample.factories.FiguraGeometricaFactory;



/**
 *
 * @author castelao
 */
public class PatronesAbstractFactoryPatternExample {

    private static final boolean MODO_3D = true;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FiguraGeometricaFactory fgFactory = FiguraGeometricaFactory.getFactory(MODO_3D);
        
        FiguraGeometrica fg = fgFactory.getCirculo();
        
        System.out.println("Es un círculo: " + (fg instanceof Circulo));
        
        System.out.println("Es una esfera: " + (fg instanceof Esfera));
    }
    
}
