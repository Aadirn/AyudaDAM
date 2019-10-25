/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Castelao
 */
public class EjercicioIO09 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader lecturador = null;
        try {
            lecturador = new FileReader("C:\\temp\\sondachina.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EjercicioIO09.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            StreamTokenizer tokenChina = new StreamTokenizer(lecturador);
            tokenChina.eolIsSignificant(true);
            while (tokenChina.nextToken() != StreamTokenizer.TT_EOF) {
                switch (tokenChina.ttype) {
                    case StreamTokenizer.TT_NUMBER:
                        System.out.println("Ha leido el numero " + (int) tokenChina.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println("Ha leido la palabra " + tokenChina.sval);
                        break;
                    case StreamTokenizer.TT_EOL:
                        System.out.println("Fin de linea");
                        break;
                    default:
                        System.out.println("Signo de puntuacion");
                        break;
                }
            }
        } catch (FileNotFoundException fnf) {
            System.err.println(fnf.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        } finally {
            try {
                lecturador.close();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }

    }

}
