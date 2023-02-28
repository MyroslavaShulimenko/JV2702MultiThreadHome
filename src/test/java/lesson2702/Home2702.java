package lesson2702;

public class Home2702 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        countDivisibility();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        countDivisibilityParallel();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
    }

    private static void countDivisibilityParallel() {

        Task task1 = new Task(Integer.MIN_VALUE,-10000000);
        Task task2 = new Task(-10000000, 10000000);
        Task task3 = new Task(10000000, Integer.MAX_VALUE);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
       Thread thread3 = new Thread(task3);
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println("Total divisible by: " + (task1.count + task2.count + task3.count));
            //System.out.println("Total divisible by :"   + (task1.count + task2.count ));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int start;
        int end;
        int count = 0;
        int j=7;

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                if (i % j == 0) {
                    count++;
                }
            }

            System.out.println("Numbers of primes: " + count);
         //   System.out.println(Integer.MAX_VALUE);
                }
        }

    private static void countDivisibility() {
        int count = 0;
int number = 7;
        for (int i=Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            if (i % number == 0)
                count++;
        }
        System.out.println("Total divisible by " + number + ":" + count);
    }

}

