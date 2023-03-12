package lesson0103;

public class ThreadWithFlag {

    static long counter = 0;
    static volatile boolean flag = true;

    static class Task implements Runnable {

        @Override
        public void run() {
       //     while (!Thread.interrupted()) {
                while (flag) {
                    counter++;
                    if (counter % 100_000 == 0) {
                        System.out.println(counter);
                    }
                }
            }
        }


        public static void main(String[] args) {
            Thread thread = new Thread(new Task());
            thread.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           // thread.interrupt();
             flag = false;

            // 2021600000
            // 2734400000

        }


    }
//    Имеется счет BankAccount, на котором хранится определенная сумма денег int sum.
//        Реализовать потокобезопасный метод пополнения счета - deposit(int amount), снятия со счета - withdraw(int amount).
//public synchronized void deposit(int amount){
//
//}
//    public synchronized void withdraw(int amount){
//
//
//    }

//public class BankAccount {
//
//    private int sum;
//
//    public synchronized void deposit(int amount) {
//        sum = sum + amount;
//    }
//
//    public synchronized void withdraw(int amount) {
//        sum = sum - amount;
//    }
//
//    public synchronized int getSum(int amount) {
//        return sum;
//    }
//
//
//    public static void main(String[] args) {
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.deposit(100);
//        bankAccount.withdraw(100);
//
//        synchronized (bankAccount) {
//
//        }
//    }
//}
