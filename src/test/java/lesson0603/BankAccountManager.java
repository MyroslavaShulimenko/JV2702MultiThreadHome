package lesson0603;

import lesson0103.BankAccount;

public class BankAccountManager {

    public static void main(String[] args) {
      //  BankAccountWithInnerMutex bankAccount=new BankAccountWithInnerMutex();
       BankAccount bankAccount=new BankAccount();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    bankAccount.deposit(100);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    bankAccount.withdraw(100);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        synchronized (bankAccount){
while (true){
    System.out.println(""+bankAccount.getSum());

    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
   }
    }
}
