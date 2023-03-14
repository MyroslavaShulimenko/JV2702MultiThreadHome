package lesson1303;

public class Ex1 {
    static volatile boolean shouldRun = false;
    static Object lunch = new Object();
    static Object duest = new Object();

    static class Visitor implements Runnable {

        @Override
        public void run() {

//            while (!shouldRun) {
//                // do nothing // busy wait
////                Thread.sleep(100);
//            }
            synchronized (duest) {
                System.out.println(Thread.currentThread().getName() + " go!");
                try {
                    duest.notify();
                    System.out.println(Thread.currentThread().getName() + " is waiting");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lunch){

                }
            }

            while (true) {
                System.out.println(Thread.currentThread().getName() + " mading");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }
    public static class Chef implements Runnable{

        @Override
        public void run() {
            synchronized (duest){
                System.out.println(Thread.currentThread().getName() + " taking");
                try {
                    duest.notify();
                    System.out.println(Thread.currentThread().getName() + " is maiding");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true) {
                System.out.println(Thread.currentThread().getName() + " mading");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Race is going to start");
        new Thread(new Visitor()).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(new Chef()).start();

        System.out.println("!!!");
        // shouldRun=true;
    }
}
