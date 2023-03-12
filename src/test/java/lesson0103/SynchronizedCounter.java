package lesson0103;

public class SynchronizedCounter {


    static int count = 0;
static Object mutex=new Object();
static Object mutex2=new Object();
    static class A implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (mutex){System.out.println(count++);}

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            while (true) {synchronized (mutex){System.out.println(count++);}
                //System.err.println(count++);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        synchronized (mutex){

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}






