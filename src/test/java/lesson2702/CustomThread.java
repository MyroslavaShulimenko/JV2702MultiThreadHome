package lesson2702;

public class CustomThread extends Thread {
    @Override
    public void run(){
        System.out.println(Thread.currentThread()+"start");
        while (!Thread.interrupted()){
        try {
            System.out.println("++++++");
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            System.out.println(Thread.currentThread()+"awaken");
            Thread.currentThread().interrupt();
        }

    }
}}
