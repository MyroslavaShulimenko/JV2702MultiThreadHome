package lesson0603;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private  static AtomicInteger coumter=new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                while (true){
                System.out.println(coumter.incrementAndGet());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }}
        };
        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                while (true){
                System.out.println(coumter.incrementAndGet());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }}
        };
        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }
}
