/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioio21;

import java.io.Serializable;

public class Customer implements Serializable {

    private String name;
    private String birthDate;
    private int rank;
    private static int count;
    private transient double credit;

    public Customer(String name, String birthDate, int rank, double credit) {
        this.name = name;
        this.birthDate = birthDate;
        this.credit = credit;
        this.rank = rank;
        count++;
    }

    @Override
    public String toString() {
        return "Customer{" + "Nombre= " + name + ", Nacimiento= " + birthDate + ", Count= " + count + ", Rango= " + rank + ", Credito= " + credit + '}';
    }

}
