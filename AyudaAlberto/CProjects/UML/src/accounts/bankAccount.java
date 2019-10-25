package accounts;

public abstract class bankAccount {

    private String owner;
    private Dollars balance;

    /*public bankAccount(String owner, Dollars balance) {
        this.owner = owner;
        this.balance = balance;
    }*/

    public void deposit(Dollars amount) {
        //balance=balance + amount;
    }

    public abstract void withdrawal(Dollars amount);
    //balance=balance-amount;

}
