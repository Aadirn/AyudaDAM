package ejercicioio19;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Castelao
 */
public class ValorNegativoException extends Exception {
    public ValorNegativoException(String mensa){
        super(mensa);
    }
    public String valorNegativo(){
        return "Se ha leido un valor negativo";
    }
}
