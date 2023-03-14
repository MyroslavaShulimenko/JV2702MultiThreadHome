package lesson1303;

public class BlockingQueueUser implements Runnable {
    private MyBlockingQueue<String>queue;
    public void Produser(MyBlockingQueue<String>queue){
        this.queue=queue;
    }
    @Override
    public void run() {

    }
    static class Consumer implements Runnable{

        @Override
        public void run() {

        }
    }
}
