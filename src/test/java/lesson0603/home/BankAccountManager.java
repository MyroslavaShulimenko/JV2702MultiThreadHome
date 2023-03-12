package lesson0603.home;

    public class BankAccountManager {
        public static void main(String[] args) {
            BankAccount bankAccount = new BankAccount();

            new Thread(() -> {
                while (true) {
                    bankAccount.deposit(222);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    bankAccount.withdraw(222);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

         //   synchronized (bankAccount) {
                while (true) {
                    System.out.println(bankAccount.getSum());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
           // }
        }
    }

