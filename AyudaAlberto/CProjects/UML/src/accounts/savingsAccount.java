/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

/**
 *
 * @author Castelao
 */
public class savingsAccount extends bankAccount {

    private Percentage annualInterestRate;

    /*public savingsAccount(String owner, Dollars balance, Percentage annualInterestRate) {
        super(owner, balance);
        this.annualInterestRate = annualInterestRate;
    }*/

    public void depositMonthlyInterest() {

    }

    @Override
    public void withdrawal(Dollars amount) {
        //balance=balance-amount;
    }
}
