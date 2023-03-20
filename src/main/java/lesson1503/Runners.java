package lesson1503;

import java.util.concurrent.CountDownLatch;

public class Runners {
    static CountDownLatch latch=new CountDownLatch(3);
    static  class Runner implements    Runnable{

        @Override
        public void run() {
            System.out.println("2323232");

                try {latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                    while (true){
                        try {
                            System.out.println("7878787");

                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);

                            }
                        }
                    }

        }



    public static void main(String[] args) {
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
       // new Thread(new Runner()).start();
        System.out.println("rrr");try {
        latch.countDown();

            Thread.sleep(3000);

        System.out.println("sss");
        latch.countDown();
            Thread.sleep(3000);
        System.out.println("ggg");
        latch.countDown();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }}