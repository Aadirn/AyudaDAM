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
public class checkingAccount extends bankAccount {

    private Dollars insufficientFee;

    /*public checkingAccount(String owner, Dollars balance, Dollars insufficientFee) {
        super(owner, balance);
        this.insufficientFee = insufficientFee;

    }*/

    public void processCheck(Check checkToProcess) {

    }

    @Override
    public void withdrawal(Dollars amount) {
        //balance=balance-amount;
    }

}
