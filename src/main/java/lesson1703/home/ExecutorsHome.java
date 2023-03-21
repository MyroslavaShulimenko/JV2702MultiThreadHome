package lesson1703.home;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsHome {
    static class House implements Callable {

       Integer numberHouse;
        Random random = new Random();
        public House(Integer numberHouse) {
            this.numberHouse = numberHouse;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " started  "+numberHouse);
            try {
                Thread.sleep(500+random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  System.out.println(Thread.currentThread().getName() + " finished  "+numberHouse);
            return (10000+random.nextInt(10000));
        }
    }

    public static void main(String[] args) throws Exception {
        Integer totalSum = 0;
           ExecutorService houses = Executors.newFixedThreadPool(4); // Executors.newFixedThreadPool(10) если по кол-ву домов
          // ExecutorService houses =Executors.newSingleThreadExecutor() ;  - одна бригада


        for (int i = 1; i <= 10; i++) {
            Future<Integer> future = houses.submit(new House(i));
            System.out.println("house "+i +" finish. Prise -  "+future.get());
            totalSum += future.get();

        }
        houses.shutdown();
        System.out.println(" Final price of houses " + 10 + " building is: " + totalSum);
    }
}
