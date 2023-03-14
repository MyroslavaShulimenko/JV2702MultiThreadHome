package lesson1303;

public class Runners {
    static volatile boolean shouldRun=false;// не оптимальный вариант -загружает процессор
     static Object mutex=new Object();
    static class Runner implements Runnable{

        @Override
        public void run() {
         //   while (!shouldRun){}
            synchronized (mutex){
                System.out.println(Thread.currentThread().getName()+"is waiting");
                try {
                    mutex.wait();
                    System.out.println(Thread.currentThread().getName()+"got signal");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (true){
            System.out.println(Thread.currentThread().getName()+"is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }}
    }

    public static void main(String[] args) {
        System.out.println("Race is going to start");
        new Thread(new Runner(), String.valueOf(1)).start();
        new Thread(new Runner(), String.valueOf(2)).start();
        new Thread(new Runner(), String.valueOf(3)).start();
        new Thread(new Runner(), String.valueOf(4)).start();
        new Thread(new Runner(), String.valueOf(5)).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Run!!!");
       // shouldRun=true;
        synchronized (mutex){
            mutex.notifyAll();
            System.out.println("Ready!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Stedy!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
