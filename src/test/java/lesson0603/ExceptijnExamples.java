package lesson0603;

public class ExceptijnExamples {
    public static void main(String[] args) {

Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("+++++"+t.getName()+"-----"+e.getMessage());
    }
});
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "works");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    throw new RuntimeException("Something wrong");
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {

                    throw new RuntimeException("Something wrong");
                }

        };
Thread thread1= new Thread(runnable,"new Tread");
thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Speshial logic1 for exceptions. Thread: " + t.getName() + ". Message: " + e.getMessage());
    }
});
thread1.start();
new Thread(runnable2).start();

        System.out.println(Thread.currentThread().getName()+" works");
        System.out.println(1/0);
    }

};
//public class ExceptionExamples {
//
//    public static void main(String[] args) {
//
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println("Default logic for exceptions. Thread: " + t.getName() + ". Message: " + e.getMessage());
//            }
//        });
//
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                while (true)
//                {
//                    System.out.println(Thread.currentThread().getName() + " works");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    throw new RuntimeException("Something wrong");
//                }
//            }
//        };
//

//        Runnable runnable2 = new R
//
//public class StateAsObjectRaceCondition {
//
//    static class BankAccount {
//        int a = 1000;
//        int b = 0;
//    }
//
//    static class AccountManager {
//        BankAccount account;
//
//        public AccountManager(BankAccount account) {
//            this.account = account;
//        }
//
//        public synchronized void moveMoney(int amount) {
//            try {
//                account.a = account.a - amount;
//                Thread.sleep(500);
//                account.b = account.b + amount;
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public synchronized BankAccount getAccount() {
//            BankAccount bankAccount = new BankAccount();
//            bankAccount.a = account.a;
//            bankAccount.b = account.b;
//            return bankAccount;
//          //  return account;
//        }
//    }
//}
//    public static void main(String[] args) {
//
//        BankAccount bankAccount =