package lesson2702;

public class ThreadExamles {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Main starts");
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread());
//
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread() + " started");
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(Thread.currentThread() + " finished");
//            }
//        };
//
//        Runnable runnable2 = () -> System.out.println("New operations 2 in " + Thread.currentThread());
//
//
//        Thread thread1 = new Thread(runnable1, "New thread");
//        Thread thread2 = new Thread(runnable2, "New thread");
//        System.out.println("Threads created");
//        Thread.sleep(5000);
//
//        thread1.start();
//        thread2.start();
//
//        System.out.println("Main ends");
        Thread customThread=new CustomThread();
        customThread.start(); // нах в параллельном потоке
        //customThread.run(); //находимся в основном потоке

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {

        }

        customThread.interrupt();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {

        }
        customThread.interrupt();
    }}
