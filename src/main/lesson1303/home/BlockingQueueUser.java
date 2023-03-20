package lesson1303.home;

import java.util.Scanner;

public class BlockingQueueUser {

    static class Producer implements Runnable {

        private MyBlockingQueue<String> queue;

int size;
        public Producer(MyBlockingQueue<String> queue,int size) {
            this.queue = queue;
this.size=size;
        }

        @Override
        public void run() {
            for (int i = 0; i < size; i++) {
                Scanner massage = new Scanner(System.in);
                System.out.println("Enter the number.exit - is the exit from the program. ");
                String massages = massage.nextLine();
                if (!massages.equals("exit")) {
                    queue.put(massages);

                    System.out.println(Thread.currentThread().getName() + ": Message     " + massages);
                } else {
                    queue.put(massages);

                    System.out.println("You entered - exit!");
                    break;
                }


            }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        static class Consumer implements Runnable {

            private MyBlockingQueue<String> queue;
            private boolean flag = true;

            public Consumer(MyBlockingQueue<String> queue) {
                this.queue = queue;
            }

            @Override
            public void run() {
                while (flag) {
                    // String massage=queue.take();
                    while (queue.take().equals("exit")) {
                        System.out.println("Exit!!!!");
                        System.out.println(Thread.currentThread().getName() + " received message: " + queue.take() +
                                " Elements in queue:" + queue.getSize());
                        flag = false;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.take();
                    System.out.println(Thread.currentThread().getName() + " received message: " + queue.take() +
                            " Elements in queue:" + queue.getSize());
break;

                }
            }

        }


        public static void main(String[] args) {

            MyBlockingQueue<String> queue = new MyBlockingQueue<>();
            Producer producer = new Producer(queue,4);
            Consumer consumer1 = new Consumer(queue);
            //  Consumer consumer2 = new Consumer(queue);
         Thread thread1=   new Thread(producer);
         thread1.start();
          Thread thread2=  new Thread(consumer1);
          thread2.start();
            //   new Thread(consumer2).start();

        }

    }



