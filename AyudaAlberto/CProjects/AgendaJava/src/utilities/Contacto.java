/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Castelao
 */
public class Contacto {
    
    private String nombre;
    private long telefono;
    private char tipo;
    private Fecha fecha;

    public Contacto(String nombre, long telefono, Fecha fecha,char tipo ) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Contacto() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String toContacto() {
        return nombre+"#"+telefono+"#"+fecha.toFecha()+"#"+tipo;
    }
    
    
    
}
