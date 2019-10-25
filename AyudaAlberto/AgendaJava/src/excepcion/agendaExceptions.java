/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 *
 * @author Castelao
 */
public class agendaExceptions extends Exception {

    public agendaExceptions(String message) {
        super(message);
    }

    class agendaLlenaException extends Exception {

        public agendaLlenaException(String message) {
            super(message);
        }
    }
    
    class maxIntentosException extends Exception {

        public maxIntentosException(String message) {
            super(message);
        }
    }

}
