package lesson0603;

import lesson0103.BankAccount;

public class StateAsObjectRaceAccount {
   static class BankAccount{
        int a=1000;
        int b=0;
    }
    static class AccountMabager{
       BankAccount account;


    public AccountMabager(BankAccount account) {
       this.account=account;
    }
    public synchronized void moveMoney(int amout){
        account.a=account.a-amout;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        account.b=account.b+amout;
    }
    public synchronized BankAccount getAccount(){
        BankAccount bankAccount=new BankAccount();
        bankAccount.b=account.b;
        bankAccount.a=account.a;
        return bankAccount;
       // return account;
    }
    }

    public static void main(String[] args) {
        BankAccount bankAccount=new BankAccount();
        AccountMabager mabager=new AccountMabager(bankAccount);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    mabager.moveMoney(10);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        while (true){
            BankAccount account=mabager.getAccount();
            System.out.println("a  "+account.a+"b   "+account.b+ "sum     "+(account.a+account.b));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
