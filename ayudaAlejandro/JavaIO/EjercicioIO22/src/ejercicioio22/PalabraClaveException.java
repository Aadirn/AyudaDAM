/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio22;

/**
 *
 * @author Castelao
 */
public class PalabraClaveException extends Exception {

    private int fila;
    private int col;

    public PalabraClaveException(String mensa) {
        super(mensa);
    }

    public PalabraClaveException(int fila, int col) {
        this.col = col;
        this.fila = fila;
    }
    @Override
    public String getMessage(){
        return "La palabra clave esta en la columna: "+fila+" y fila: "+col;
    }
}
