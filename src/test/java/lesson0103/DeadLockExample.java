package lesson0103;

public class DeadLockExample {
    static Object snack = new Object();
    static Object drink = new Object();

    static class Cat implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (snack) {
                    System.out.println(Thread.currentThread().getName() + " started eating");
                    synchronized (drink) {
                        System.out.println(Thread.currentThread().getName() + " started drinking");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " finished drinking");
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                       // System.out.println((e.printStackTrace());
                    }

                }
            }}}

    public static void main(String[] args) {
        new Thread(new Cat(),"Tom").start();
        new Thread(new Cat(),"Dog").start();
    }
}