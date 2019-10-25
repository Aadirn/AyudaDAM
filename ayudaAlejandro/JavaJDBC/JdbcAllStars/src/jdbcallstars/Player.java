/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcallstars;

public class Player implements Comparable {

    private int codigo;
    private String nombre;
    private String posicion;
    private float puntuacion;

    public Player(int codigo, String nombre, String posicion, float puntuacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.posicion = posicion;
        this.puntuacion = puntuacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public int compareTo(Object t) {
        Player p = ((Player) t);

        if (this.getPuntuacion() == p.getPuntuacion()) {
            return 0;
        } else if (this.getPuntuacion() < p.getPuntuacion()) {
            return 1;
        } else {
            return -1;
        }
    }
}
