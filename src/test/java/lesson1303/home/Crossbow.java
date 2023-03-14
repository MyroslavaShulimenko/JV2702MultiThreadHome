package lesson1303.home;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Crossbow {

static ReentrantLock lock=new ReentrantLock();
        private int arrows ;

        // When the arrows end, the wait() method is called and releases.
        synchronized public void fire() {
            lock.lock();
            System.out.println("Count arrows = ");
            Scanner scanner=new Scanner(System.in);
            arrows= scanner.nextInt();
            for (int idx = arrows; idx >= 0; idx--) {

                if (idx != 0) {
                    System.out.println("The arrow is " + (arrows - idx + 1) + " right on the target!");
                } else {
                    System.out.println("The arrows are over");
                    arrows = 0;
                    System.out.println("Count arrows " + arrows);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Carry a new quiver with arrows!!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }

        // reload() brings new arrows, calls the notify() method, which awakens the thread
        synchronized public void reload() {
            System.out.println("New arrows on the way!");
        //    arrows = 5;
            System.out.println("Count arrows = " + arrows);
            notify();
        }

        public static void main(String[] args) {
while (true){
            Crossbow crossbow = new Crossbow();

            Thread robinHood = new Thread(crossbow::fire);
            Thread servant = new Thread(crossbow::reload);

            robinHood.start();

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            servant.start();
        }}
    }


